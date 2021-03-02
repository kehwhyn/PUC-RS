//Kevin Boucinha
/* Includes ------------------------------------------------------------------*/
#include "main.h"

/* Private includes ----------------------------------------------------------*/
/* USER CODE BEGIN Includes */
#include "stdlib.h"
#include "string.h"
/* USER CODE END Includes */

/* Private typedef -----------------------------------------------------------*/
/* USER CODE BEGIN PTD */
typedef unsigned char byte;
/* USER CODE END PTD */

/* Private define ------------------------------------------------------------*/
/* USER CODE BEGIN PD */

//Interface de comunicacao do display
//Pinos de controle
#define PIN_RS_SET (GPIOA->BSRR = 1 << 9) // PA9
#define PIN_RS_RESET (GPIOA->BSRR = 1 << 25)
#define PIN_EN_SET (GPIOC->BSRR = 1 << 7) // PC7
#define PIN_EN_RESET (GPIOC->BSRR = 1 << 23)
#define PIN_BACKLIGHT_SET (GPIOB->BSRR = 1 << 6) // PB6
#define PIN_BACKLIGHT_RESET (GPIOB->BSRR = 1 << 22)
//Pinos de dados
#define D7_1 (GPIOA->BSRR = 1 << 8) // PA8
#define D7_0 (GPIOA->BSRR = 1 << 24)
#define D6_1 (GPIOB->BSRR = 1 << 10) // PB10
#define D6_0 (GPIOB->BSRR = 1 << 26)
#define D5_1 (GPIOB->BSRR = 1 << 4) // PB4
#define D5_0 (GPIOB->BSRR = 1 << 20)
#define D4_1 (GPIOB->BSRR = 1 << 5) //PB5
#define D4_0 (GPIOB->BSRR = 1 << 21)
/* USER CODE END PD */

/* Private macro -------------------------------------------------------------*/
/* USER CODE BEGIN PM */
//Modos do cursor
#define CURSOR_LIGADO 0x0E
#define CURSOR_DESLIGADO 0x0C
#define CURSOR_PISCANTE 0x0F

#define LED_1 (GPIOC->BSRR = 1 << 12) // PC12
#define LED_0 (GPIOC->BSRR = 1 << 28)
#define RELE_1 (GPIOC->BSRR = 1 << 10) // PC10
#define RELE_0 (GPIOC->BSRR = 1 << 26)

#define arraySize 13
/* USER CODE END PM */

/* Private variables ---------------------------------------------------------*/

/* USER CODE BEGIN PV */
const char combinacao[arraySize][3] = {"E6", "E5", "E4", "E3", "E2", "E1", " 0", "D1", "D2", "D3", "D4", "D5", "D6"};
const int valor[arraySize] = {315, 630, 945, 1260, 1575, 1890, 2205, 2520, 2835, 3150, 3465, 3780, 4095};
const char minhaSenha[13] = {"D1E6D2D1E4D1"}; //16204042. Como nao tem D0, pus D1
int numeroDeCombinacoes = 0, jaAbriUmaVez = 0;
char senhaUsuario[16], combinacaoDigitada[3], ultimaCombinacaoDigitada[3];
/* USER CODE END PV */

/* Private function prototypes -----------------------------------------------*/
void SystemClock_Config(void);
static void MX_GPIO_Init(void);
static void MX_USART2_UART_Init(void);
static void MX_ADC_Init(void);
/* USER CODE BEGIN PFP */
//Funcoes dadas pelo professor
void uDelay(void);
void delayUs(int);
void lcdWriteCommand4Bits(byte);
void lcdWriteCommand(byte);
void lcdWriteCharacter(byte);
void lcdWriteString(byte *);
int fputc(int, FILE *);
void lcdInitialize(byte);
void lcdGoto(byte, byte);
//Funcoes dos alunos
int getBit(byte, byte);
void lcdSendData(byte);
void genericStuff(void);
int lePotenciometro(void);
void travar(void);
void confirmarCombinacao(void);
void avaliarCombinacao(void);
void compararSenhas(void);
void mostrarCombinacao(void);
void combinacaoCorreta(void);
void limparCombinacao(void);
void cofre(void);
/* USER CODE END PFP */

/* Private user code ---------------------------------------------------------*/
int main(void)
{
	/* USER CODE BEGIN 2 */
	lcdInitialize(CURSOR_PISCANTE);
	PIN_BACKLIGHT_SET;
	memset(senhaUsuario, 0, sizeof(senhaUsuario));
	memset(combinacaoDigitada, 0, sizeof(combinacaoDigitada));
	memset(ultimaCombinacaoDigitada, 0, sizeof(ultimaCombinacaoDigitada));
	/* USER CODE END 2 */

	/* Infinite loop */
	/* USER CODE BEGIN WHILE */
	while (1)
	{
		/* USER CODE END WHILE */

		/* USER CODE BEGIN 3 */

		cofre();
	}
	/* USER CODE END 3 */
}

/* USER CODE BEGIN 4 */
void uDelay(void)
{

	int x = 10;

	while (x)
		x--;
}

void delayUs(int tempo)
{

	while (tempo--)
		uDelay();
}

void lcdWriteCommand4Bits(byte com)
{
	lcdSendData(com);
	PIN_RS_RESET;
	genericStuff();
	HAL_Delay(5);
}

void lcdWriteCommand(byte com)
{
	lcdSendData(com / 0x10);
	PIN_RS_RESET;
	genericStuff();
	delayUs(5);
	lcdSendData(com % 0x10);
	genericStuff();
	HAL_Delay(5);
}

void lcdWriteCharacter(byte ch)
{
	lcdSendData(ch / 0x10);
	PIN_RS_SET;
	genericStuff();
	delayUs(5);
	lcdSendData(ch % 0x10);
	genericStuff();
	HAL_Delay(5);
}

void lcdInitialize(byte estado)
{

	lcdWriteCommand4Bits(3); // comandos em 4 bits
	lcdWriteCommand4Bits(3);
	lcdWriteCommand4Bits(3);
	lcdWriteCommand4Bits(2);
	lcdWriteCommand(0x28);	 // interface de 4 bits
	lcdWriteCommand(estado); // cursor
	lcdWriteCommand(0x06);	 // shift
	lcdWriteCommand(0x01);	 //clear
}

void lcdGoto(byte x, byte y)
{

	if (y == 0)
		lcdWriteCommand(0x80 + x);
	else if (y == 1)
		lcdWriteCommand(0xC0 + x);
	else if (y == 2)
		lcdWriteCommand(0x90 + x);
	else if (y == 3)
		lcdWriteCommand(0xD0 + x);
}

void lcdWriteString(byte *str)
{

	while (*str)
		lcdWriteCharacter(*(str++));
}

int fputc(int ch, FILE *f)
{

	lcdWriteCharacter(ch);
	return ch;
}

int getBit(byte valor, byte posicao)
{

	return valor & (1 << posicao);
}

void lcdSendData(byte x)
{

	(getBit(x, 3)) ? D7_1 : D7_0;
	(getBit(x, 2)) ? D6_1 : D6_0;
	(getBit(x, 1)) ? D5_1 : D5_0;
	(getBit(x, 0)) ? D4_1 : D4_0;
}

void genericStuff(void)
{

	PIN_EN_SET;
	delayUs(5);
	PIN_EN_RESET;
}

int lePotenciometro(void)
{

	int leitura = 0;

	HAL_ADC_Start(&hadc);
	HAL_ADC_PollForConversion(&hadc, 1);
	leitura = HAL_ADC_GetValue(&hadc);
	HAL_ADC_Stop(&hadc);

	for (int posicao = 0; posicao < arraySize; posicao++)

		if (leitura <= valor[posicao])
			return posicao;
}

void travar(void)
{

	/* O cofre inicia nesta posisao e nesta condicao (TRAVAR) */
	lcdWriteCommand(0x01);
	lcdGoto(0, 0);
	printf("TRAVAR");
	/* Um LED vermelho eh usado como feedback visual do acionamento da destrava */
	LED_0;
	/*	Nesta situacao o microcontrolador envia um pulso de 350ms para abrir
	 * a porta por meio de um solenoide (rele).
	 */
	RELE_0;
	HAL_Delay(350);
	confirmarCombinacao();
}

void confirmarCombinacao(void)
{

	for (int contDeConfirmacao = 0, posicao = 0; contDeConfirmacao < 10;)
	{

		posicao = lePotenciometro();
		/*	Quando a combinacao nao trocar de valor por pelo menos 2 segundos
		 * entao o programa entendera que esta eh uma combinaco digitada
		 */
		if (!strcmp(" 0", combinacao[posicao]))
		{

			lcdGoto(14, 0);
			printf(" 0");
			strcpy(combinacaoDigitada, combinacao[posicao]);
			contDeConfirmacao++;
		}
		else if (!strcmp(combinacaoDigitada, combinacao[posicao]))

			contDeConfirmacao++;

		else
		{

			contDeConfirmacao = 0;
			/*	Na medida que voce vai ajustando o potenciometro, a combinacao
			 * vai sendo mostrada no display (canto superior direito)
			 */
			strcpy(combinacaoDigitada, combinacao[posicao]);
			lcdGoto(14, 0);
			printf("%s", combinacaoDigitada);
		}

		HAL_Delay(185);
	}

	avaliarCombinacao();
}

void avaliarCombinacao(void)
{

	if (strlen(senhaUsuario) == strlen(minhaSenha))

		compararSenhas();

	/* 	Se o usuario digitou seis combinacoes (corretas ou nao) e digitar
	 * uma nova combinacao, ele apaga as combinacoes ja digitadas.
	 * 	Se o usuario estiver digitando sua senha e a qualquer momento colocar
	 * o potenciometro na posicao 0 por 2 ou mais segundos, a senha eh
	 * interrompida
	 */
	else if (!strcmp(" 0", combinacaoDigitada))

		limparCombinacao();

	else
		mostrarCombinacao();
}

void compararSenhas(void)
{

	/*	Se todas elas estiverem corretas o cofre muda de status
	 * de TRAVAR para ABERTO.
	 */
	if (!strcmp(minhaSenha, senhaUsuario))

		combinacaoCorreta();

	else

		limparCombinacao();
}

void mostrarCombinacao(void)
{

	/* nao eh permitido combinacao repetidas de forma sucessiva */
	if (strcmp(ultimaCombinacaoDigitada, combinacaoDigitada))
	{

		strcpy(ultimaCombinacaoDigitada, combinacaoDigitada);
		strcat(senhaUsuario, combinacaoDigitada);
		/* e vai  mostrando a combinacao na linha inferior do display */
		lcdGoto(0, 1);
		printf("%s", senhaUsuario);
	}

	confirmarCombinacao();
}

void combinacaoCorreta(void)
{

	int posicao = 0;

	lcdWriteCommand(0x01);
	lcdGoto(0, 0);
	printf("ABERTO");
	/* Um LED vermelho eh usado como feedback visual do acionamento da destrava */
	LED_1;
	/*	Nesta situacao o microcontrolador envia um pulso de 350ms para abrir
	 * a porta por meio de um solenoide (rele).
	 */
	RELE_1;
	HAL_Delay(350);

	do
	{

		for (int cont = 0; cont < 10;)
		{

			posicao = lePotenciometro();

			if (!strcmp(" 0", combinacao[posicao]))

				cont++;

			else
			{

				cont = 0;
				lcdGoto(14, 0);
				printf("%s", combinacao[posicao]);
				HAL_Delay(185);
			}
		}
	} while (strcmp(" 0", combinacao[posicao]));

	memset(senhaUsuario, 0, sizeof(senhaUsuario));
	memset(combinacaoDigitada, 0, sizeof(combinacaoDigitada));
	memset(ultimaCombinacaoDigitada, 0, sizeof(ultimaCombinacaoDigitada));
	travar();
}

void limparCombinacao(void)
{

	/* e as combinacoes ja feitas sao limpas */
	memset(senhaUsuario, 0, sizeof(senhaUsuario));
	memset(combinacaoDigitada, 0, sizeof(combinacaoDigitada));
	memset(ultimaCombinacaoDigitada, 0, sizeof(ultimaCombinacaoDigitada));
	lcdGoto(0, 1);
	printf("              ");
	confirmarCombinacao();
}

void cofre(void)
{

	travar();
}