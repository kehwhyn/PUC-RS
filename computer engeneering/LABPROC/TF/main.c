/* Includes ------------------------------------------------------------------*/
#include "main.h"

/* Private includes ----------------------------------------------------------*/
/* USER CODE BEGIN Includes */
#include "stdio.h"
#include "stdlib.h"
#include "string.h"
/* USER CODE END Includes */

/* Private typedef -----------------------------------------------------------*/
/* USER CODE BEGIN PTD */
typedef unsigned char byte;
/* USER CODE END PTD */

/* Private define ------------------------------------------------------------*/
/* USER CODE BEGIN PD */
//INTERFACE COM DISPLAY
#define D7_1 	(GPIOB->BSRR = 1 << 3) 	//PB3
#define D7_0 	(GPIOB->BSRR = 1 << 19)
#define D6_1 	(GPIOB->BSRR = 1 << 4)	//PB4
#define D6_0 	(GPIOB->BSRR = 1 << 20)
#define D5_1 	(GPIOB->BSRR = 1 << 5) 	//PB5
#define D5_0 	(GPIOB->BSRR = 1 << 21)
#define D4_1 	(GPIOB->BSRR = 1 << 6) 	//PB6
#define D4_0 	(GPIOB->BSRR = 1 << 22)
#define PIN_RS_SET 				 	(GPIOB->BSRR = 1 << 7) 	//PB9
#define PIN_RS_RESET 				(GPIOB->BSRR = 1 << 23)
#define PIN_EN_SET 				 	(GPIOB->BSRR = 1 << 8) 	//PB8
#define PIN_EN_RESET 				(GPIOB->BSRR = 1 << 24)
#define PIN_BACKLIGHT_SET 	(GPIOB->BSRR = 1 << 9) 	//PB9
#define PIN_BACKLIGHT_RESET (GPIOB->BSRR = 1 << 25)
#define CURSOR_LIGADO 		0x0E
#define CURSOR_DESLIGADO 	0x0C
#define CURSOR_PISCANTE 	0x0F
//Botao de confirmar a hora
#define CONFIRMAR (GPIOA->IDR & 1 << 4) //PA4
//Controle do fio de leds
#define LED_DATA_SET 		(GPIOB->BSRR = 1 << 0) 	//PB0
#define LED_DATA_RESET 	(GPIOB->BSRR = 1 << 16)
//Controle do Infravermelho
#define INFRARED_PIN_SET (GPIOB->BSRR = 1 << 1) //PB1
#define INFRARED_PIN_RESET (GPIOB->BSRR = 1 << 17)
/* USER CODE END PD */
/* Private macro -------------------------------------------------------------*/
/* USER CODE BEGIN PM */
#define ARRAY_SIZE	60
/* USER CODE END PM */
/* Private variables ---------------------------------------------------------*/
ADC_HandleTypeDef hadc1;
ADC_HandleTypeDef hadc2;

CRC_HandleTypeDef hcrc;

RTC_HandleTypeDef hrtc;

TIM_HandleTypeDef htim1;

UART_HandleTypeDef huart2;
UART_HandleTypeDef huart3;

PCD_HandleTypeDef hpcd_USB_FS;

/* USER CODE BEGIN PV */
RTC_TimeTypeDef relogio;
RTC_DateTypeDef calendario;

const int colors[40] = { //GRB
	0x000000, 0xFFFFFF, 0x10000F, 0xF00015, 0xF00010, 0xF0000F, 0xF000FF, 0xF0F00F, 0xF0000F, 0x00F00F,
	0xFF99CC, 0xFF50CC, 0xCC99CC, 0xFF9950, 0xFF0099, 0xFF99CC, 0x800040, 0xCC0040, 0x4000CC, 0x66ff77,
	0x6666FF, 0xff6666, 0xFF0000, 0x66ff66, 0x50ff66, 0x0000FF, 0x66ff50, 0x665066, 0x77ff66, 0x66ff66,
	0x0F0000, 0x010203, 0x019940, 0x014099, 0x01CC00, 0x01EC30, 0x40EF00, 0x99EF00, 0x00F099, 0x00FF00,
};
const int InfraredCodePART1[77]={0x0000, 0x0071 , 0x0000 , 0x0002 , 0x0000 , 0x00AA , 0x0000 , 0x0040 , 0x0000 , 
0x0040 , 0x0000 , 0x0015 , 0x0000 , 0x0015 , 0x0000 , 0x0015 , 0x0000 , 0x0015 , 0x0000 , 0x0015 , 0x0000, 
0x0040 , 0x0000 , 0x0040 , 0x0000 , 0x0015 , 0x0000 , 0x0040 , 0x0000 , 0x0015 , 0x0000 , 0x0040 , 0x0000, 
0x0015 , 0x0000 , 0x0040 , 0x0000 , 0x0015 , 0x0000 , 0x0015 , 0x0000 , 0x0015 , 0x0000 , 0x0015 , 0x0000, 
0x0015 , 0x0000 , 0x0040 , 0x0000 , 0x0015 , 0x0000 , 0x0015 , 0x0000 , 0x003F , 0x0000 , 0x0040 , 0x0000,
0x0040 , 0x0000 , 0x003F , 0x0000 , 0x0040 , 0x0000 , 0x0015 , 0x0000 , 0x0040 , 0x0000 , 0x0040 , 0x0000,
0x0015 , 0x0000 , 0x0611 , 0x0000 , 0x0055 , 0x0000 , 0x0E40
};
const int InfraredCodePART2[39] = { 0x0015 ,0x0015 ,0x0040 ,0x0015 ,0x0015 ,0x0015 ,0x0015 ,0x0015 ,0x0015 ,
0x0015 ,0x0015 ,0x0015 ,0x0015, 0x0015 ,0x0040 ,0x0015 ,0x0015 ,0x0015 ,0x0015 ,0x0015 ,0x0040 ,0x0015 ,
0x0040 ,0x0015 ,0x0040 ,0x0015, 0x0040 ,0x0015 ,0x0040 ,0x0015 ,0x0015 ,0x0015 ,0x0040 ,0x0015 ,0x0040 ,
0x0015 ,0x0015 ,0x0015 ,0x05C4
};
uint8_t volatile rec_data[8];
uint8_t trans_data[10] = {'\0'};

const int  valores[60]  = {68,137,205,273,341,410,478,546,614,683,751,819,887,956,1024,1092,1160,1229,
	1297,1365,1433,1502,1570,1638,1706,1775,1843,1911,1979,2048,2116,2184,2252,2321,2389,2457,2525,2594,2662,2730,
	2798,2867,2935,3003,3071,3140,3208,3276,3344,3413,3481,3549,3617,3686,3754,3822,3890,3959,4027,4095};

int volatile mode = 0;
/* USER CODE END PV */

/* Private function prototypes -----------------------------------------------*/

/* USER CODE BEGIN PFP */
//Funcoes dadas pelo professor
void uDelay(int);
void delayUs(int);
void lcdWriteCommand4Bits(byte);
void lcdWriteCommand(byte);
void lcdWriteCharacter(byte);
void lcdWriteString(byte*);
int  fputc(int, FILE*);
void lcdInitialize(byte);
void lcdGoto(byte, byte);
//Funcoes dos alunos
int  getBit(byte, byte);
void lcdSendData(byte);
void genericStuff(void);
//Funcoes para o LED STRIP
void LED_PULSE_1();
void LED_PULSE_0();
void showLED(int, int);
void LED_RESET();
void LED_Animation(int);
//Funcoes para o IR
void InfraRed();
void InfraRed_PULSE_0();
void InfraRed_PULSE_1();
//Funcoes para o relogio
void setHora();
void showTime();
int indexOf(int);
int lePotenciometro();
/* USER CODE END PFP */
/* Private user code ---------------------------------------------------------*/

int main(void)
{
  /* USER CODE BEGIN 1 */
		int leitura;
  /* USER CODE END 1 */

  /* USER CODE BEGIN 2 */
	memset(trans_data, 0, sizeof(trans_data));
	lcdInitialize(CURSOR_DESLIGADO);
	LCD_CreateChar();
	PIN_BACKLIGHT_SET;
		
	__HAL_TIM_CLEAR_FLAG(&htim1, TIM_FLAG_UPDATE);
	__HAL_TIM_CLEAR_FLAG(&htim1, TIM_IT_UPDATE);
	__HAL_TIM_CLEAR_FLAG(&htim1, TIM_SR_UIF);
	HAL_TIM_Base_Start_IT(&htim1);
	__HAL_TIM_CLEAR_FLAG(&htim1, TIM_FLAG_UPDATE);
	__HAL_TIM_CLEAR_FLAG(&htim1, TIM_IT_UPDATE);
	__HAL_TIM_CLEAR_FLAG(&htim1, TIM_SR_UIF);
	strcpy((char *)trans_data, "Conectado");
	HAL_UART_Transmit(&huart3, (uint8_t *)trans_data, 10, 10);  
	HAL_UART_Receive_IT(&huart3, (uint8_t *)rec_data,3);
	__HAL_UART_ENABLE_IT(&huart3, UART_IT_RXNE);
	
	showTime();
  /* USER CODE END 2 */

  /* Infinite loop */
  /* USER CODE BEGIN WHILE */
  while (1){

    /* USER CODE BEGIN 3 */
		if(mode == 0) {			
			LED_RESET();
			showLED(1, 12);// "white"
			delayUs(1000000);
		} else if(mode == 1) {
			LED_RESET();
			showLED(0, 12);// turned off
			HAL_ADC_Start(&hadc1);
			HAL_ADC_PollForConversion(&hadc1, 1);
			leitura = HAL_ADC_GetValue(&hadc1);
			HAL_ADC_Stop(&hadc1);
				
			if(leitura < 1500) {
				LED_RESET();
				showLED(0, 12);// turned off
			} else {
				LED_RESET();
				showLED(40*(leitura-1500)/2500, 15*leitura/3000);
			} 
			delayUs(40000);	
			
		} else if(mode == 2) {
			for(int j = 0; j < 4; j++) {
				for(int i = 0; i < 20; i++) {
					showLED(i+j,1);
				}
					delayUs(500000);
			}
		} else if(mode == 3) {
			for(int i = 0; i < 40; i++){
				showLED(i,12);
				delayUs(200000);	
			}	
		} else {
			LED_Animation(1);
			delayUs(10000);	
		}
		__HAL_UART_ENABLE_IT(&huart3, UART_IT_RXNE);	
		HAL_UART_Receive_IT(&huart3, (uint8_t *)rec_data,3);	
  }
  /* USER CODE END 3 */
}

/* USER CODE BEGIN 4 */
void uDelay(int x) {
	while(x) x--;
}

void delayUs(int tempo) {
	while(tempo--) uDelay(10);
}

void lcdWriteCommand4Bits(byte com) {
	lcdSendData(com);
	PIN_RS_RESET;
	genericStuff();
	delayUs(5000);
}

void lcdWriteCommand(byte com) {
	lcdSendData(com/0x10);
	PIN_RS_RESET;
	genericStuff();
	delayUs(5);
	lcdSendData(com%0x10);
	genericStuff();
	delayUs(5000);
}

void lcdWriteCharacter(byte ch) {
	lcdSendData(ch/0x10);
	PIN_RS_SET;
	genericStuff();
	delayUs(5);
	lcdSendData(ch%0x10);
	genericStuff();
	delayUs(5000);
}

void lcdInitialize(byte estado) {
	lcdWriteCommand4Bits(3); // comandos em 4 bits
	lcdWriteCommand4Bits(3);
	lcdWriteCommand4Bits(3);
	lcdWriteCommand4Bits(2);
	lcdWriteCommand(0x28);   // interface de 4 bits
	lcdWriteCommand(estado); // cursor
	lcdWriteCommand(0x06);   // shift
	lcdWriteCommand(0x01);   //clear
}

void lcdGoto(byte x, byte y) {
	if(y == 0) 			lcdWriteCommand(0x80 + x);
	else if(y == 1) lcdWriteCommand(0xC0 + x);
	else if(y == 2) lcdWriteCommand(0x90 + x);
	else if(y == 3) lcdWriteCommand(0xD0 + x);
}

void lcdWriteString(byte *str) {
	while(*str) lcdWriteCharacter(*(str++));
}

int fputc(int ch, FILE *f) {
	lcdWriteCharacter(ch);
	return ch;
}

int getBit(byte valor, byte posicao) {
	return valor & (1 << posicao);
}

void lcdSendData(byte x) {
	(getBit(x, 3)) ? D7_1 : D7_0;
	(getBit(x, 2)) ? D6_1 : D6_0;
	(getBit(x, 1)) ? D5_1 : D5_0;
	(getBit(x, 0)) ? D4_1 : D4_0;
}

void genericStuff(void) {
	PIN_EN_SET;
	delayUs(5);
	PIN_EN_RESET;
}

void LED_RESET(){
  LED_DATA_RESET;
  delayUs(50);
}
void showLED(int i, int times) {
	for(int k = 0; k < times; k ++)
		for(int j = 0; j < 24; j++){
			if ((colors[i] & (1 << (23-j))) == 0) LED_PULSE_0();
			else LED_PULSE_1();
	}	
}
void LED_Animation(int times) {
	for(int i = 0; i < 1024; i = i + 20)
		for(int j = 0; j < 1024; j = j + 20)
			for(int k = 0; k < 1024; k = k + 20) {
				
				LED_RESET();
				for(int l = 0; l < 12; l++) {					
					for(int m = 0; m < 8; m++) {
						if ((i & (1 << m)) == 0) LED_PULSE_0();
						else LED_PULSE_1();
					}
					for(int m = 0; m < 8; m++) {
						if ((j & (1 << m)) == 0) LED_PULSE_0();
						else LED_PULSE_1();
					}
					for(int m = 0; m < 8; m++) {
						if ((k & (1 << m)) == 0) LED_PULSE_0();
						else LED_PULSE_1();
					}
					delayUs(1000);
				}
			}
}
void LED_PULSE_0() {  
	LED_DATA_SET;
	uDelay(4);
	LED_DATA_RESET;
	uDelay(8);
}
void LED_PULSE_1() {
  LED_DATA_SET;	
	uDelay(8);
  LED_DATA_RESET;
	uDelay(4);
}
void InfraRed() {
	INFRARED_PIN_SET;  
	delayUs(9000);;
	INFRARED_PIN_RESET;
	delayUs(4500);
	for(int i = 0; i < 77; i++)
		for(int j = 0; j < 16; j++)
		  if ((InfraredCodePART1[i] & (1 << (16-j))) == 0) InfraRed_PULSE_0();
		  else InfraRed_PULSE_1();
	for(int i = 0; i < 39; i++)  
		for(int j = 0; j < 16; j++)
		  if ((InfraredCodePART2[i] & (1 << (16-j))) == 0) InfraRed_PULSE_0();
		  else InfraRed_PULSE_1();

	lcdGoto(0,0);		
	printf("SENT");
	delayUs(500000);
	printf("                   ");
	showTime();
}

void InfraRed_PULSE_1() {
	for(int i; i < 25; i++) {
			INFRARED_PIN_SET;
			delayUs(11);
			INFRARED_PIN_RESET;
			delayUs(11);
	}
  INFRARED_PIN_RESET;
  delayUs(1687);
}

void InfraRed_PULSE_0() {
  for(int i; i < 25; i++) {
			INFRARED_PIN_SET;
			delayUs(11);
			INFRARED_PIN_RESET;
			delayUs(11);
	}
	INFRARED_PIN_RESET;
  delayUs(562);
}

//Interrupcao dos botoes
void HAL_GPIO_EXTI_Callback(uint16_t GPIO_Pin) {	
		__HAL_GPIO_EXTI_CLEAR_IT(GPIO_Pin);
    
    if(GPIO_Pin == GPIO_PIN_5) //Botao MODE eh pressionado
    {
      if(mode == 0) mode = 1;
      else mode = 0;
		}
		if(GPIO_Pin == GPIO_PIN_6) //Botao ON_PROJETOR eh pressionado
    {
			InfraRed();
		}
    if(GPIO_Pin == GPIO_PIN_7) //Botao SET_HORA eh pressionado
    {
			setHora();
		}
}
//Interrupcao do timer
void HAL_TIM_PeriodElapsedCallback(TIM_HandleTypeDef *htim) {		
	  if (htim->Instance == htim1.Instance) {
			showTime();
		}	
}
//Interrupcao da UART
void HAL_UART_RxCpltCallback(UART_HandleTypeDef *huart) {
  
	if(huart->Instance == huart3.Instance) {	
		if(!strcmp((char *)rec_data,"on ")) {
			InfraRed();
			//HAL_UART_Transmit(&huart2,(uint8_t *)"Projetor ligado", 16, 10);
			lcdGoto(0,0);
			printf("Projetor ligado");
			delayUs(500000);
			printf("                   ");
			lcdWriteCommand(0x01);
			showTime();
		}
		else if(!strcmp((char *)rec_data, "mod")) {
			mode++;
			if(mode == 4) mode = 0;
		}
		memset((uint8_t *)rec_data,0,8);
		__HAL_UART_ENABLE_IT(&huart3, UART_IT_RXNE);
		HAL_UART_Receive_IT(&huart3, (uint8_t *)rec_data,3);		
	} 
}

void setHora(void) {

	int aux = 0;
	
	//definir hora
	while(CONFIRMAR) {
		aux = lePotenciometro();
		aux = indexOf(aux);
		if(aux > 23) aux = 0;
		relogio.Hours = aux;
		lcdGoto(5,0);
		printf("%02d:00", relogio.Hours);
	}
	while(!CONFIRMAR){};
	
	//definir minuto
	while(CONFIRMAR) {
		aux = lePotenciometro();
		relogio.Minutes = indexOf(aux);
		lcdGoto(8,0);
		printf("%02d", relogio.Minutes);
	}		
	//definir segundos
	relogio.Seconds = 0;
	lcdWriteCommand(0x01);
	delayUs(1000000);
	//Setar hora no uC
	HAL_RTC_SetTime(&hrtc, &relogio, RTC_FORMAT_BIN);
	showTime();
}
void showTime(){
	HAL_RTC_GetTime(&hrtc, &relogio, RTC_FORMAT_BIN);
	HAL_RTC_GetDate(&hrtc, &calendario, RTC_FORMAT_BIN);
	lcdGoto(5,0);
	printf("%02d:%02d", relogio.Hours, relogio.Minutes);
}
int lePotenciometro(void) {
  int leitura = 0;

  HAL_ADC_Start(&hadc2);
  HAL_ADC_PollForConversion(&hadc2, 1);
  leitura = HAL_ADC_GetValue(&hadc2);
  HAL_ADC_Stop(&hadc2);

	return leitura;
}

int indexOf(int leitura) {
	for(int posicao = 0; posicao < ARRAY_SIZE; posicao++)
		if(leitura <= valores[posicao]) return posicao;
}

/* USER CODE END 4 */