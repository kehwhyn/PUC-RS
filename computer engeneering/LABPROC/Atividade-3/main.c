//Nomes: Kevin Fiorentin, Nédison Velloso e Yasmin Elkfury
/Tarefa 3

#include "main.h"
#include "string.h"

typedef unsigned char byte;

// PINOS CONTROLE DISPLAY
#define PIN_RS_SET 			(GPIOA->BSRR = 1 << 9) // PA9
#define PIN_RS_RESET 		(GPIOA->BSRR = 1 << 25)
#define PIN_EN_SET 			(GPIOC->BSRR = 1 << 7) // PC7
#define PIN_EN_RESET 		(GPIOC->BSRR = 1 << 23)
#define PIN_BACKLIGHT_SET 	(GPIOB->BSRR = 1 << 6) // PB6
#define PIN_BACKLIGHT_RESET (GPIOB->BSRR = 1 << 22)

// CURSOR
#define CURSOR_LIGADO 		0x0E
#define CURSOR_DESLIGADO 	0x0C
#define CURSOR_PISCANTE 	0x0F

// INTERFACE COM DISPLAY
#define D7_1 	(GPIOA->BSRR = (1 << 8)) //PA8
#define D7_0 	(GPIOA->BSRR = (1 << 24))
#define D6_1 	(GPIOB->BSRR = (1 << 10))//PB10
#define D6_0 	(GPIOB->BSRR = (1 << 26))
#define D5_1 	(GPIOB->BSRR = (1 << 4)) //PB4
#define D5_0 	(GPIOB->BSRR = (1 << 20))
#define D4_1 	(GPIOB->BSRR = (1 << 5)) //PB5
#define D4_0 	(GPIOB->BSRR = (1 << 21))

// ENTRADAS TECLADO -> OPEN-DRAIN
#define LER_LINHA0 (GPIOC->IDR & (1 << 3)) //PC3 -> AMARELO
#define LER_LINHA1 (GPIOC->IDR & (1 << 2)) //PC2 -> VERDE
#define LER_LINHA2 (GPIOC->IDR & (1 << 4)) //PC4 -> PRETO
#define LER_LINHA3 (GPIOB->IDR & (1 << 13))//PB13 -> BRANCO

// SAIDAS TECLADO -> PULL-UP
#define ESC_COL0_1 (GPIOA->BSRR = (1 << 15)) //PA15 -> AZUL
#define ESC_COL0_0 (GPIOA->BSRR = (1 << 31))
#define ESC_COL1_1 (GPIOC->BSRR = (1 << 12)) //PC12 -> VERMELHO
#define ESC_COL1_0 (GPIOC->BSRR = (1 << 28))
#define ESC_COL2_1 (GPIOC->BSRR = (1 << 10)) //PC10 -> LARANJA
#define ESC_COL2_0 (GPIOC->BSRR = (1 << 26))

// FUNCOES DE AULA
int fputc(int, FILE *);
void uDelay();
void delayUs(int);
void LCD_WRCOM4(byte);
void LCD_WRCOM(byte);
void LCD_INIT(byte);
void LCD_GOTO(byte, byte);
void LCD_WRCHAR(byte);
void LCD_WRSTR(byte *);

//FUNCOES FEITAS PELOS ALUNOS
void genericStuff();
int  getBit(byte, byte);
void setPorta(byte);
byte scanning();
byte readKeyboard();
void setHorario(int *,int *,int *);
void setData(int *,int *,int *);
void checkBacklightHour(int);
void mudaDia(int, int);
int bissexto(int);

int main(void) {
	int hora, minuto, segundo,
		dia, mes, ano;
	const int dia_aux[12] = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	
		LCD_INIT(CURSOR_PISCANTE);
	PIN_BACKLIGHT_SET;
	
	setHorario(&hora, &minuto, &segundo);
	LCD_WRCOM(0x01);
	setData(&dia, &mes, &ano);
	LCD_WRCOM(0x01);
	
	LCD_WRCOM(CURSOR_DESLIGADO);
	LCD_GOTO(0,0);
	
	LCD_GOTO(0,0);
	printf("Hora:");
	LCD_GOTO(8,0);
	printf("%02d:%02d:%02d", hora, minuto, segundo);
	
	 while (1) {
	 		
 		LCD_GOTO(0,1);
		printf("Data:");
		LCD_GOTO(6,1);
		printf("%02d/%02d/%04d", dia, mes, ano);
		
		for(int auxHora = hora; auxHora < 24; auxHora++) {
			for(int auxMinuto = minuto; auxMinuto < 60; auxMinuto++) {
				for(int auxSegundo = segundo; auxSegundo< 60; auxSegundo++) {
					
					LCD_GOTO(8,0);
					printf("%02d:%02d:%02d", auxHora, auxMinuto, auxSegundo);
					 
					checkBacklightHour(auxHora);
					
					mudaDia(auxHora, auxSegundo);
					
				} segundo = 0;
			} minuto = 0;
		} hora = 0;
		 
		if(bissexto(ano) && mes == 2 && dia == 29)  dia = 1, mes++;
		else if(dia == 28 && dia_aux[mes-1] == dia) dia = 1, mes++;
		else if(dia == 30 && dia_aux[mes-1] == dia) dia = 1, mes++; 
		else if(dia == 31 && dia_aux[mes-1] == dia) { 
		
			dia = 1;
		
			if(mes == 12) mes = 1, ano++; else mes++;
		}
		else dia++;
  }
}

void uDelay() {
	
	int x = 10;
	
	while(x) x--;
}

void delayUs(int tempo) {
	
	while(tempo--) uDelay();
}

int fputc(int ch, FILE *f) {

	LCD_WRCHAR(ch);
	return ch;
}

void LCD_WRCOM4(byte com) {

	setPorta(com);
	PIN_RS_RESET;
	PIN_EN_SET;
	delayUs(5);
	PIN_EN_RESET;
	HAL_Delay(5);
}

void LCD_WRCOM(byte com) {

	setPorta(com/0x10);
	PIN_RS_RESET;
	PIN_EN_SET;
	delayUs(5);
	PIN_EN_RESET;
	delayUs(5);
	setPorta(com%0x10);
	PIN_EN_SET;
	delayUs(5);
	PIN_EN_RESET;	
	HAL_Delay(5);
}

void LCD_INIT(byte estado) {
	
	LCD_WRCOM4(3); 		// comandos em 4 bits
	LCD_WRCOM4(3);
	LCD_WRCOM4(3);
	LCD_WRCOM4(2);
	LCD_WRCOM(0x28); 	// interface de 4 bits
	LCD_WRCOM(estado); 	// cursor
	LCD_WRCOM(0x06); 	// shift
	LCD_WRCOM(0x01); 	//clear
}

void LCD_GOTO(byte x, byte y) {

	if(y == 0) LCD_WRCOM(0x80 + x);
	else if(y == 1) LCD_WRCOM(0xC0 + x);
	else if(y == 2) LCD_WRCOM(0x90 + x);
	else if(y == 3) LCD_WRCOM(0xD0 + x);
}

void LCD_WRCHAR(byte ch) {
	setPorta(ch/0x10);
	PIN_RS_SET;
	PIN_EN_SET;
	delayUs(5);
	PIN_EN_RESET;
	delayUs(5);
	setPorta(ch%0x10);
	PIN_EN_SET;
	delayUs(5);
	PIN_EN_RESET;	
	HAL_Delay(5);
}

void LCD_WRSTR(byte *str) {
	
	while(*str) LCD_WRCHAR(*(str++));
}

int getBit(byte valor, byte shift) {

	return valor & (1 << shift);
}

void setPorta(byte x) {

	if(getBit(x, 3)) D7_1; else D7_0;
	if(getBit(x, 2)) D6_1; else D6_0;
	if(getBit(x, 1)) D5_1; else D5_0;
	if(getBit(x, 0)) D4_1; else D4_0;
}

void genericStuff() {

	PIN_EN_SET;
	delayUs(5);
	PIN_EN_RESET;
}

byte scanning() {
	
	while(1) {
		
		ESC_COL0_0;
		ESC_COL1_1;
		ESC_COL2_1;
    	HAL_Delay(4);
		
		if(LER_LINHA0 == 0) return '1';
		if(LER_LINHA1 == 0) return '4';
		if(LER_LINHA2 == 0) return '7';
		if(LER_LINHA3 == 0) return '*';
		
		ESC_COL0_1;
		ESC_COL1_0;
		ESC_COL2_1;
    	HAL_Delay(4);

		if(LER_LINHA0 == 0) return '2';
		if(LER_LINHA1 == 0) return '5';
		if(LER_LINHA2 == 0) return '8';
		if(LER_LINHA3 == 0) return '0';
		
		ESC_COL0_1;
		ESC_COL1_1;
		ESC_COL2_0;
    	HAL_Delay(4);

		if(LER_LINHA0 == 0) return '3';
		if(LER_LINHA1 == 0) return '6';
		if(LER_LINHA2 == 0) return '9';
		if(LER_LINHA3 == 0) return '#';

		return '-';
	}
}

byte readKeyboard() {
	
	byte button;

	do {
		
		button = scanning();
		HAL_Delay(250);
	
	} while(button == '-');
		
	return button;
}

void setHorario(int *hora, int *minuto, int *segundo) {
	
	byte dezena,
			 unidade,
			 conf,
			 estado = 0;
	 
	LCD_GOTO(0,0);
	printf("Digite o horario:");
	LCD_GOTO(0,1);
	printf("00:00:00");
	
	// HORA
	dezena = readKeyboard();
	LCD_GOTO(0,1);
	printf("%02d:00:00", (dezena - '0'));

	unidade = readKeyboard();
	LCD_GOTO(0,1);
	*hora = (dezena - '0')*10 + (unidade - '0');
	printf("%02d:00:00", *hora);
			
	dezena = readKeyboard();
	LCD_GOTO(0,1);
	printf("%02d:%02d:00", *hora, (dezena - '0'));
	
	unidade = readKeyboard();
	LCD_GOTO(0,1);
	*minuto = (dezena - '0')*10 + (unidade - '0');
	printf("%02d:%02d:00", *hora, *minuto);

	dezena = readKeyboard();
	LCD_GOTO(0,1);
	printf("%02d:%02d:%02d", *hora, *minuto, (dezena - '0'));
	
	unidade = readKeyboard();
	LCD_GOTO(0,1);
	*segundo = (dezena - '0')*10 + (unidade - '0');
	printf("%02d:%02d:%02d", *hora, *minuto, *segundo);

}

void setData(int *dia, int *mes, int *ano) {
	
	byte dezena,
			 unidade,
			 conf,
			 estado = 0;
	 
	LCD_GOTO(0,0);
	printf("Digite a data:");
	LCD_GOTO(0,1);
	printf("00/00/0000");
	
	// DIA
	dezena = readKeyboard();
	LCD_GOTO(0,1);
	printf("%02d/00/0000", (dezena - '0'));

	unidade = readKeyboard();
	LCD_GOTO(0,1);
	*dia = (dezena - '0')*10 + (unidade - '0');
	printf("%02d/00/0000", *dia);	
	
	// MES
	dezena = readKeyboard();
	LCD_GOTO(0,1);
	printf("%02d/%02d/0000", *dia, (dezena - '0'));

	unidade = readKeyboard();
	LCD_GOTO(0,1);
	*mes = (dezena - '0')*10 + (unidade - '0');
	printf("%02d/%02d/0000", *dia, *mes);

	// ANO
	dezena = readKeyboard();
	LCD_GOTO(0,1);
	*ano = (dezena - '0')*1000;
	printf("%02d/%02d/%04d", *dia, *mes, *ano);
	
	unidade = readKeyboard();
	LCD_GOTO(0,1);
	*ano += (unidade - '0') * 100;
	printf("%02d/%02d/%04d", *dia, *mes, *ano);
	
	dezena = readKeyboard();
	LCD_GOTO(0,1);
	*ano += (dezena - '0') * 10;
	printf("%02d/%02d/%04d", *dia, *mes, *ano);
	
	unidade = readKeyboard();
	LCD_GOTO(0,1);
	*ano += (unidade - '0');
	printf("%02d/%02d/%04d", *dia, *mes, *ano);
}

void checkBacklightHour(int hora) {

	if (hora < 8 || hora > 18) PIN_BACKLIGHT_SET;
	else PIN_BACKLIGHT_RESET;
}
