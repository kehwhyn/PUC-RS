/* USER CODE BEGIN Header */
/**
  ******************************************************************************
  * @file           : main.c
  * @brief          : Main program body
  ******************************************************************************
  ** This notice applies to any and all portions of this file
  * that are not between comment pairs USER CODE BEGIN and
  * USER CODE END. Other portions of this file, whether 
  * inserted by the user or by software development tools
  * are owned by their respective copyright owners.
  *
  * COPYRIGHT(c) 2019 STMicroelectronics
  *
  * Redistribution and use in source and binary forms, with or without modification,
  * are permitted provided that the following conditions are met:
  *   1. Redistributions of source code must retain the above copyright notice,
  *      this list of conditions and the following disclaimer.
  *   2. Redistributions in binary form must reproduce the above copyright notice,
  *      this list of conditions and the following disclaimer in the documentation
  *      and/or other materials provided with the distribution.
  *   3. Neither the name of STMicroelectronics nor the names of its contributors
  *      may be used to endorse or promote products derived from this software
  *      without specific prior written permission.
  *
  * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
  * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
  * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
  * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
  * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
  * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
  * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
  * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
  * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
  * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
  *
  ******************************************************************************
  */
/* USER CODE END Header */

/* Includes ------------------------------------------------------------------*/
#include "main.h"

/* Private includes ----------------------------------------------------------*/
/* USER CODE BEGIN Includes */

/* USER CODE END Includes */

/* Private typedef -----------------------------------------------------------*/
/* USER CODE BEGIN PTD */

typedef unsigned char byte;

/* USER CODE END PTD */

/* Private define ------------------------------------------------------------*/
/* USER CODE BEGIN PD */

//Pinos de controle
#define PIN_RS_SET 					(GPIOA->BSRR = 1 << 9) // PA9
#define PIN_RS_RESET 				(GPIOA->BSRR = 1 << 25)
#define PIN_EN_SET 					(GPIOC->BSRR = 1 << 7) // PC7
#define PIN_EN_RESET 				(GPIOC->BSRR = 1 << 23)
#define PIN_BACKLIGHT_SET 	(GPIOB->BSRR = 1 << 6) // PB6
#define PIN_BACKLIGHT_RESET (GPIOB->BSRR = 1 << 22)

//INTERFACE COM DISPLAY
#define D7_1 	(GPIOA->BSRR = 1 << 8) // PA8
#define D7_0 	(GPIOA->BSRR = 1 << 24)
#define D6_1 	(GPIOB->BSRR = 1 << 10)// PB10
#define D6_0 	(GPIOB->BSRR = 1 << 26)
#define D5_1 	(GPIOB->BSRR = 1 << 4) // PB4
#define D5_0 	(GPIOB->BSRR = 1 << 20)
#define D4_1 	(GPIOB->BSRR = 1 << 5) //PB5
#define D4_0 	(GPIOB->BSRR = 1 << 21)
//Cursor
#define CURSOR_LIGADO 		0x0E
#define CURSOR_DESLIGADO 	0x0C
#define CURSOR_PISCANTE 	0x0F

#define no_LCD 1
#define na_UART 2
char aonde = no_LCD;
/* USER CODE END PD */

/* Private macro -------------------------------------------------------------*/
/* USER CODE BEGIN PM */

/* USER CODE END PM */

/* Private variables ---------------------------------------------------------*/
ADC_HandleTypeDef hadc;

UART_HandleTypeDef huart2;

/* USER CODE BEGIN PV */

/* USER CODE END PV */

/* Private function prototypes -----------------------------------------------*/
void SystemClock_Config(void);
static void MX_GPIO_Init(void);
static void MX_USART2_UART_Init(void);
static void MX_ADC_Init(void);
/* USER CODE BEGIN PFP */
void uDelay(void);
void delayUs(int tempo);
int fputc(int ch, FILE *f);
void LCD_INIT(byte estado);
void LCD_GOTO(byte x, byte y);
void LCD_WRCOM(byte COM);
void LCD_4BIT_WRCOM(byte COM);
void LCD_WRCHAR(byte CH);
void LCD_WRSTR(byte *str);

void lcd_wrcom4(byte com);
void lcd_wrcom(byte com);
void lcd_wrchar(byte ch);



//FUNCOES FEITAS PELOS ALUNOS
int  testaBit(byte aTestar, byte posicao);
void setBitPorta(byte COM4, int shift, int porta);
void coisaQueSeRepeteMuito();
void passaDados(byte COM4);
char readKeyboard();
void checkBacklightHour();
/* USER CODE END PFP */

/* Private user code ---------------------------------------------------------*/
/* USER CODE BEGIN 0 */

/* USER CODE END 0 */

/**
  * @brief  The application entry point.
  * @retval int
  */
int main(void)
{
  /* USER CODE BEGIN 1 */
	int leitura;
  float Xv, Xc;

  /* USER CODE END 1 */

  /* MCU Configuration--------------------------------------------------------*/

  /* Reset of all peripherals, Initializes the Flash interface and the Systick. */
  HAL_Init();

  /* USER CODE BEGIN Init */

  /* USER CODE END Init */

  /* Configure the system clock */
  SystemClock_Config();

  /* USER CODE BEGIN SysInit */

  /* USER CODE END SysInit */

  /* Initialize all configured peripherals */
  MX_GPIO_Init();
  MX_USART2_UART_Init();
  MX_ADC_Init();
  /* USER CODE BEGIN 2 */
	LCD_INIT(CURSOR_PISCANTE);
	PIN_BACKLIGHT_SET;
  /* USER CODE END 2 */

  /* Infinite loop */
  /* USER CODE BEGIN WHILE */
  while (1)
  {
    /* USER CODE END WHILE */

    /* USER CODE BEGIN 3 */
		LCD_WRCOM(0x01);
		HAL_ADC_Start(&hadc);
    HAL_ADC_PollForConversion(&hadc, 1);
    leitura = HAL_ADC_GetValue(&hadc);
    HAL_ADC_Stop(&hadc);
		//leitura da tensao
		
      Xv = leitura*0.000805; //leitura*3,3/4095  tensão
      aonde = no_LCD;			
			if (leitura < 1000) LCD_GOTO(13,0);
			else LCD_GOTO(12,0);
      printf("%d", leitura)	;					
      LCD_GOTO(0,1);
      printf("%.1f Volts", Xv);
      aonde = na_UART;
      printf("Tensao(absoluto): %d\n", leitura);
      printf("Tensao(convertido): %.1fV\n", Xv);
    
		//leitura da temperatura
/*
      //Xc = leitura*0.000806; //leitura*1.5/1861  celcius
      Xc = ((330*(leitura-140))/4095)-4;  //celcius
      aonde = no_LCD;		
			if (leitura < 1000) LCD_GOTO(13,0);
			else LCD_GOTO(12,0);
      printf("%d", leitura);				
      LCD_GOTO(0,1);
      printf("%.1f%cC", Xc,223);
      aonde = na_UART;
      printf("Temperatura(absoluto): %d\n", leitura);
      printf("Temperatura(convertido): %.1fV\n", Xc);
  
    HAL_Delay(925);
  */}
  }
  /* USER CODE END 3 */


/**
  * @brief System Clock Configuration
  * @retval None
  */
void SystemClock_Config(void)
{
  RCC_OscInitTypeDef RCC_OscInitStruct = {0};
  RCC_ClkInitTypeDef RCC_ClkInitStruct = {0};
  RCC_PeriphCLKInitTypeDef PeriphClkInit = {0};

  /**Initializes the CPU, AHB and APB busses clocks 
  */
  RCC_OscInitStruct.OscillatorType = RCC_OSCILLATORTYPE_HSI14|RCC_OSCILLATORTYPE_HSI48;
  RCC_OscInitStruct.HSI48State = RCC_HSI48_ON;
  RCC_OscInitStruct.HSI14State = RCC_HSI14_ON;
  RCC_OscInitStruct.HSI14CalibrationValue = 16;
  RCC_OscInitStruct.PLL.PLLState = RCC_PLL_NONE;
  if (HAL_RCC_OscConfig(&RCC_OscInitStruct) != HAL_OK)
  {
    Error_Handler();
  }
  /**Initializes the CPU, AHB and APB busses clocks 
  */
  RCC_ClkInitStruct.ClockType = RCC_CLOCKTYPE_HCLK|RCC_CLOCKTYPE_SYSCLK
                              |RCC_CLOCKTYPE_PCLK1;
  RCC_ClkInitStruct.SYSCLKSource = RCC_SYSCLKSOURCE_HSI48;
  RCC_ClkInitStruct.AHBCLKDivider = RCC_SYSCLK_DIV1;
  RCC_ClkInitStruct.APB1CLKDivider = RCC_HCLK_DIV1;

  if (HAL_RCC_ClockConfig(&RCC_ClkInitStruct, FLASH_LATENCY_1) != HAL_OK)
  {
    Error_Handler();
  }
  PeriphClkInit.PeriphClockSelection = RCC_PERIPHCLK_USART2;
  PeriphClkInit.Usart2ClockSelection = RCC_USART2CLKSOURCE_PCLK1;
  if (HAL_RCCEx_PeriphCLKConfig(&PeriphClkInit) != HAL_OK)
  {
    Error_Handler();
  }
}

/**
  * @brief ADC Initialization Function
  * @param None
  * @retval None
  */
static void MX_ADC_Init(void)
{

  /* USER CODE BEGIN ADC_Init 0 */

  /* USER CODE END ADC_Init 0 */

  ADC_ChannelConfTypeDef sConfig = {0};

  /* USER CODE BEGIN ADC_Init 1 */

  /* USER CODE END ADC_Init 1 */
  /**Configure the global features of the ADC (Clock, Resolution, Data Alignment and number of conversion) 
  */
  hadc.Instance = ADC1;
  hadc.Init.ClockPrescaler = ADC_CLOCK_ASYNC_DIV1;
  hadc.Init.Resolution = ADC_RESOLUTION_12B;
  hadc.Init.DataAlign = ADC_DATAALIGN_RIGHT;
  hadc.Init.ScanConvMode = ADC_SCAN_DIRECTION_FORWARD;
  hadc.Init.EOCSelection = ADC_EOC_SINGLE_CONV;
  hadc.Init.LowPowerAutoWait = DISABLE;
  hadc.Init.LowPowerAutoPowerOff = DISABLE;
  hadc.Init.ContinuousConvMode = ENABLE;
  hadc.Init.DiscontinuousConvMode = DISABLE;
  hadc.Init.ExternalTrigConv = ADC_SOFTWARE_START;
  hadc.Init.ExternalTrigConvEdge = ADC_EXTERNALTRIGCONVEDGE_NONE;
  hadc.Init.DMAContinuousRequests = DISABLE;
  hadc.Init.Overrun = ADC_OVR_DATA_PRESERVED;
  if (HAL_ADC_Init(&hadc) != HAL_OK)
  {
    Error_Handler();
  }
  /**Configure for the selected ADC regular channel to be converted. 
  */
  sConfig.Channel = ADC_CHANNEL_1;
  sConfig.Rank = ADC_RANK_CHANNEL_NUMBER;
  sConfig.SamplingTime = ADC_SAMPLETIME_1CYCLE_5;
  if (HAL_ADC_ConfigChannel(&hadc, &sConfig) != HAL_OK)
  {
    Error_Handler();
  }
  /* USER CODE BEGIN ADC_Init 2 */

  /* USER CODE END ADC_Init 2 */

}

/**
  * @brief USART2 Initialization Function
  * @param None
  * @retval None
  */
static void MX_USART2_UART_Init(void)
{

  /* USER CODE BEGIN USART2_Init 0 */

  /* USER CODE END USART2_Init 0 */

  /* USER CODE BEGIN USART2_Init 1 */

  /* USER CODE END USART2_Init 1 */
  huart2.Instance = USART2;
  huart2.Init.BaudRate = 115200;
  huart2.Init.WordLength = UART_WORDLENGTH_8B;
  huart2.Init.StopBits = UART_STOPBITS_1;
  huart2.Init.Parity = UART_PARITY_NONE;
  huart2.Init.Mode = UART_MODE_TX_RX;
  huart2.Init.HwFlowCtl = UART_HWCONTROL_NONE;
  huart2.Init.OverSampling = UART_OVERSAMPLING_16;
  huart2.Init.OneBitSampling = UART_ONE_BIT_SAMPLE_DISABLE;
  huart2.AdvancedInit.AdvFeatureInit = UART_ADVFEATURE_NO_INIT;
  if (HAL_UART_Init(&huart2) != HAL_OK)
  {
    Error_Handler();
  }
  /* USER CODE BEGIN USART2_Init 2 */

  /* USER CODE END USART2_Init 2 */

}

/**
  * @brief GPIO Initialization Function
  * @param None
  * @retval None
  */
static void MX_GPIO_Init(void)
{
  GPIO_InitTypeDef GPIO_InitStruct = {0};

  /* GPIO Ports Clock Enable */
  __HAL_RCC_GPIOC_CLK_ENABLE();
  __HAL_RCC_GPIOF_CLK_ENABLE();
  __HAL_RCC_GPIOA_CLK_ENABLE();
  __HAL_RCC_GPIOB_CLK_ENABLE();

  /*Configure GPIO pin Output Level */
  HAL_GPIO_WritePin(GPIOA, LD2_Pin|GPIO_PIN_8|GPIO_PIN_9, GPIO_PIN_RESET);

  /*Configure GPIO pin Output Level */
  HAL_GPIO_WritePin(GPIOB, GPIO_PIN_10|GPIO_PIN_4|GPIO_PIN_5|GPIO_PIN_6, GPIO_PIN_RESET);

  /*Configure GPIO pin Output Level */
  HAL_GPIO_WritePin(GPIOC, GPIO_PIN_7, GPIO_PIN_RESET);

  /*Configure GPIO pin : B1_Pin */
  GPIO_InitStruct.Pin = B1_Pin;
  GPIO_InitStruct.Mode = GPIO_MODE_IT_FALLING;
  GPIO_InitStruct.Pull = GPIO_NOPULL;
  HAL_GPIO_Init(B1_GPIO_Port, &GPIO_InitStruct);

  /*Configure GPIO pins : LD2_Pin PA8 PA9 */
  GPIO_InitStruct.Pin = LD2_Pin|GPIO_PIN_8|GPIO_PIN_9;
  GPIO_InitStruct.Mode = GPIO_MODE_OUTPUT_PP;
  GPIO_InitStruct.Pull = GPIO_NOPULL;
  GPIO_InitStruct.Speed = GPIO_SPEED_FREQ_LOW;
  HAL_GPIO_Init(GPIOA, &GPIO_InitStruct);

  /*Configure GPIO pins : PB10 PB4 PB5 PB6 */
  GPIO_InitStruct.Pin = GPIO_PIN_10|GPIO_PIN_4|GPIO_PIN_5|GPIO_PIN_6;
  GPIO_InitStruct.Mode = GPIO_MODE_OUTPUT_PP;
  GPIO_InitStruct.Pull = GPIO_NOPULL;
  GPIO_InitStruct.Speed = GPIO_SPEED_FREQ_LOW;
  HAL_GPIO_Init(GPIOB, &GPIO_InitStruct);

  /*Configure GPIO pin : PC7 */
  GPIO_InitStruct.Pin = GPIO_PIN_7;
  GPIO_InitStruct.Mode = GPIO_MODE_OUTPUT_PP;
  GPIO_InitStruct.Pull = GPIO_NOPULL;
  GPIO_InitStruct.Speed = GPIO_SPEED_FREQ_LOW;
  HAL_GPIO_Init(GPIOC, &GPIO_InitStruct);

}

/* USER CODE BEGIN 4 */

void uDelay(void) {
	
	int x = 10;
	
	while(x) x--;
}

void delayUs(int tempo) {
	
	while(tempo--) uDelay();
}

int fputc(int ch, FILE *f) {

	if (aonde == no_LCD) lcd_wrchar(ch);
	if (aonde == na_UART) HAL_UART_Transmit(&huart2, (uint8_t *)&ch, 1, 10);
	return ch;

}

void LCD_INIT(byte estado) {
	
	lcd_wrcom4(3); // comandos em 4 bits
	lcd_wrcom4(3);
	lcd_wrcom4(3);
	lcd_wrcom4(2);
	lcd_wrcom(0x28); // interface de 4 bits
	lcd_wrcom(estado); // cursor
	lcd_wrcom(0x06); // shift
	lcd_wrcom(0x01); //clear
}

void LCD_GOTO(byte x, byte y) {

	if(y == 0)
		lcd_wrcom(0x80 + x);
	
	else if(y == 1)
		lcd_wrcom(0xC0 + x);
	
	else if(y == 2)
		lcd_wrcom(0x90 + x);
	
	else if(y == 3)
		lcd_wrcom(0xD0 + x);
}

int testaBit(byte aTestar, byte posicao){

	return aTestar & (1 << posicao);
}

void lcd_sendData(byte x)
{
	(testaBit(x, 3)) ? D7_1 : D7_0;
	(testaBit(x, 2)) ? D6_1 : D6_0;
	(testaBit(x, 1)) ? D5_1 : D5_0;
	(testaBit(x, 0)) ? D4_1 : D4_0;
}

void lcd_wrcom4(byte com)
{
	lcd_sendData(com);
	PIN_RS_RESET;
	PIN_EN_SET;
	delayUs(5);
	PIN_EN_RESET;
	HAL_Delay(5);
}

void lcd_wrcom(byte com)
{
	lcd_sendData(com/0x10);
	PIN_RS_RESET;
	PIN_EN_SET;
	delayUs(5);
	PIN_EN_RESET;
	delayUs(5);
	lcd_sendData(com%0x10);
	PIN_EN_SET;
	delayUs(5);
	PIN_EN_RESET;	
	HAL_Delay(5);
}

void lcd_wrchar(byte ch)
{
	lcd_sendData(ch/0x10);
	PIN_RS_SET;
	PIN_EN_SET;
	delayUs(5);
	PIN_EN_RESET;
	delayUs(5);
	lcd_sendData(ch%0x10);
	PIN_EN_SET;
	delayUs(5);
	PIN_EN_RESET;	
	HAL_Delay(5);
}

void LCD_WRCOM(byte COM) {
	
	PIN_RS_RESET;
	passaDados(COM/0x10);// msd
	delayUs(5);
	passaDados(COM%0x10); 	 // lsd
	HAL_Delay(5);
}

void LCD_4BIT_WRCOM(byte COM) {
	
	PIN_RS_RESET;
	passaDados(COM);
	HAL_Delay(5);
}

void LCD_WRCHAR(byte CH) {
	
	PIN_RS_SET;
	passaDados(CH/0x10); // msd
	delayUs(5);
	passaDados(CH%0x10);		 // lsd
	HAL_Delay(5);
}

void LCD_WRSTR(byte *str) {
	
	while(*str) lcd_wrchar(*(str++));
}

/* Funcao pega um padrao de bits e testa
 * o bit indicado pelo usuario, retornando
 * 1 se for verdadeiro e 0 se falso
 */


/* Gente eu nao sei. Isso se repete
 * muito no codigo, entao pus numa
 * funcao
 */
void coisaQueSeRepeteMuito() {

	PIN_EN_SET;
	delayUs(5);
	PIN_EN_RESET;
}

/* Essa funcao foi indicada pelo professor.
 * No inicio nao entendi muito bem, porem
 * apos ler um pouco ela seta as portas de
 * dados de acordo com o comando que usamos.
 * Ela e tao gerenalista que deu pra aplicar
 * em mais de um lugar do codigo. Nao sei muito
 * bem se vai funcionar, mas teoricamente acho que sim
 */
void passaDados(byte COM4) {

	setBitPorta(COM4, 3, 4);
	setBitPorta(COM4, 2, 3);
	setBitPorta(COM4, 1, 2);
	setBitPorta(COM4, 0, 1);
	coisaQueSeRepeteMuito();
}

void setBitPorta(byte COM4, int shift, int porta) {
		
	int aux = testaBit(COM4, shift);
	
	switch(porta) {
	
		case 4:
			
			(aux) ? D7_0 : D7_1;
			
		break;
		
		case 3:
		
			(aux) ? D6_0 : D6_1;
			
		break;
		
		case 2:
			
			(aux) ? D5_0 : D5_1;
			
		break;
		
		case 1:
			
			(aux) ? D4_0 : D4_1;
			
		break;
		default:
		
			PIN_BACKLIGHT_RESET;
			
		break;
	}
}
/* USER CODE END 4 */

/**
  * @brief  This function is executed in case of error occurrence.
  * @retval None
  */
void Error_Handler(void)
{
  /* USER CODE BEGIN Error_Handler_Debug */
  /* User can add his own implementation to report the HAL error return state */

  /* USER CODE END Error_Handler_Debug */
}

#ifdef  USE_FULL_ASSERT
/**
  * @brief  Reports the name of the source file and the source line number
  *         where the assert_param error has occurred.
  * @param  file: pointer to the source file name
  * @param  line: assert_param error line source number
  * @retval None
  */
void assert_failed(char *file, uint32_t line)
{ 
  /* USER CODE BEGIN 6 */
  /* User can add his own implementation to report the file name and line number,
     tex: printf("Wrong parameters value: file %s on line %d\r\n", file, line) */
  /* USER CODE END 6 */
}
#endif /* USE_FULL_ASSERT */

/************************ (C) COPYRIGHT STMicroelectronics *****END OF FILE****/
