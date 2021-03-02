library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_ARITH.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;

entity dec_cron is
    generic (
        CLOCK_FREQ : integer := 49999999
    );
    port (
    reset: in std_logic;
    clk50: in std_logic;
     carga: in std_logic;
     conta: in std_logic;
     an: out std_logic_vector(3 downto 0);
     chaves: in STD_LOGIC_VECTOR (6 downto 0);
     dec_ddp: out STD_LOGIC_VECTOR (7 downto 0)
    );
end dec_cron;

architecture dec_cron of dec_cron is
signal cont, segundos, minutos: integer := 0;
signal d1, d2, d3, d4: std_logic_vector(5 downto 0);
signal minutos_bcd, segundos_bcd: std_logic_vector(7 downto 0);
signal clk_1h : std_logic := '0';

type ROM is array (0 to 99) of std_logic_vector (7 downto 0);
constant Conv_to_BCD : ROM:=(
                "00000000", "00000001", "00000010", "00000011", "00000100",
                "00000101", "00000110", "00000111", "00001000", "00001001",  
                "00010000", "00010001", "00010010", "00010011", "00010100",
                "00010101", "00010110", "00010111", "00011000", "00011001",  
                "00100000", "00100001", "00100010", "00100011", "00100100",
                "00100101", "00100110", "00100111", "00101000", "00101001",  
                "00110000", "00110001", "00110010", "00110011", "00110100",
                "00110101", "00110110", "00110111", "00111000", "00111001",  
                "01000000", "01000001", "01000010", "01000011", "01000100",
                "01000101", "01000110", "01000111", "01001000", "01001001",  
                "01010000", "01010001", "01010010", "01010011", "01010100",
                "01010101", "01010110", "01010111", "01011000", "01011001",
                "01100000", "01100001", "01100010", "01100011", "01100100",
                "01100101", "01100110", "01100111", "01101000", "01101001",
                "01110000", "01110001", "01110010", "01110011", "01110100",
                "01110101", "01110110", "01110111", "01111000", "01111001",
                "10000000", "10000001", "10000010", "10000011", "10000100",
                "10000101", "10000110", "10000111", "10001000", "10001001",
                "10010000", "10010001", "10010010", "10010011", "10010100",
                "10010101", "10010110", "10010111", "10011000", "10011001"
);
    type states is (REP, LOAD, COUNT);
    signal EA : states;   
begin

    P1: process(clk50, reset) --divisor de clock para gerar o ck1seg
    begin
     if(reset = '1') then
            cont <= 0;
            clk_1h <= '0';
        elsif(clk50'event and clk50 = '1') then -- clk 1h
            if(cont < CLOCK_FREQ) then
                 cont <= cont + 1;
            else
                 cont <= 0;
                      clk_1h <= not(clk_1h);
            end if;
      end if;    
    end process P1;

    -- P2/P3: máquina de estados para determinar o estado atual (EA)
    P2: process(clk_1h, reset)
    begin
        if(reset = '1') then
            EA <= REP;
        elsif(clk_1h'event and clk_1h = '1') then
            if(EA = REP) then
                if(carga = '1') then
                    EA <= LOAD;
                end if;
            elsif(EA = LOAD) then
                if(conta = '1') then
                    EA <= COUNT;
                end if;
            else
                if(minutos = 0 and segundos = 0) then
                    EA <= REP;
                end if;
            end if;
        end if;
    end process P2;    
            
    P3: process(clk_1h, reset) --contador de segundos
    begin
        if(reset = '1') then
            segundos <= 0;
        elsif(clk_1h'event and clk_1h = '1') then
            if(EA = COUNT) then
                if(segundos > 0) then
                    segundos <= segundos -1;
                elsif(minutos = 0) then
                    segundos <= 0;
                else
                    segundos <= 59;
                end if;
            end if;
        end if;
    end process P3;

    P4: process(clk_1h, reset) --contador de minutos
    begin
        if(reset = '1') then
            minutos <= 0;
        elsif(clk_1h'event and clk_1h = '1') then
            if(EA = COUNT) then
                if(minutos > 0) and (segundos = 0)then
                    minutos <= minutos -1;
                end if;
            end if;
        elsif(EA = LOAD) then
            minutos <= conv_integer(chaves);
        end if;
    end process P4;

    -- instanciação das ROMs
    segundos_bcd <= conv_to_BCD(segundos);
    minutos_bcd  <= conv_to_BCD(minutos);

    -- display driver
    d1 <= '1' & segundos_bcd(3 downto 0) & '1';
    d2 <= '1' & segundos_bcd(7 downto 4) & '1';
    d3 <= '1' & minutos_bcd(3 downto 0) & '1';
    d4 <= '1' & minutos_bcd(7 downto 4) & '1';
    display_driver : entity work.dspl_drv
    port map (
    d1 => d1, d2 => d2, d3 => d3, d4 => d4, clock => clk50, reset => reset, an => an, dec_ddp => dec_ddp);       
    end dec_cron;


