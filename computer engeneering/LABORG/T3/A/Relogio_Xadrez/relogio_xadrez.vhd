library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_ARITH.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;

entity relogio_xadrez is
  generic ( CLOCK_FREQ : integer := 25000000 ); -- O generic e para parametrizar o divisor de clock
  port (
  reset, carga, j1, j2, clock: in std_logic;
  led_j1, led_j2: out std_logic;
  an: out std_logic_vector(3 downto 0);
  chaves: in STD_LOGIC_VECTOR (6 downto 0);
  dec_ddp: out STD_LOGIC_VECTOR (7 downto 0)
  );  -- Pinos conforme o diagrama de blocos ou o arquivo UCF
end relogio_xadrez;

architecture relogio_xadrez of relogio_xadrez is
  signal carga_int, j1_int, j2_int, vez_j1, vez_j2, fim_j1, fim_j2: std_logic;
  signal conta : std_logic;
  signal an_j1, an_j2: std_logic_vector(3 downto 0);
  signal dec_ddp_j1, dec_ddp_j2: std_logic_vector(7 downto 0);

  type states is (REP, LOAD, COUNT_J1a, COUNT_J1, COUNT_J2a, COUNT_J2, END_J1, END_J2);
    signal EA: states;
begin

    a1: entity work.edge_detector port map (clock =>clock, reset =>reset, din => carga, rising =>carga_int);
    a2: entity work.edge_detector port map (clock =>clock, reset =>reset, din => j1, rising =>j1_int);
    a3: entity work.edge_detector port map (clock =>clock, reset =>reset, din => j2, rising =>j2_int);

    i_cron_j1: entity work.dec_cron -- cronometro p/ jogador 1
             generic map (CLOCK_FREQ => CLOCK_FREQ)
             port map (ce => vez_j1, fim => fim_j1, chaves => chaves, reset => reset, clk50 => clock, carga => carga_int, conta => conta, an => an_j1, dec_ddp => dec_ddp_j1);

     i_cron_j2: entity work.dec_cron -- cronometro p/ jogador 2
             generic map (CLOCK_FREQ => CLOCK_FREQ)
             port map (ce => vez_j2, fim => fim_j2, chaves => chaves, reset => reset, clk50 => clock, carga => carga_int, conta => conta, an=> an_j2, dec_ddp=> dec_ddp_j2);

 meu_J1: process(clock, reset)
    begin
        if(reset = '1') then
            EA <= REP;
        elsif(clock'event and clock = '1') then
            if(EA = REP) then
            if(carga_int = '1') then
                    EA <= LOAD;
                else
                    EA <= REP;
                end if;
            elsif(EA = LOAD) then
                if(j1_int = '1' ) then
                    EA <= COUNT_J1a;
                elsif(j2_int = '1') then
                    EA <= COUNT_J2a;
                else
                    EA <= LOAD;
                end if;
            elsif(EA = COUNT_J1a) then
                if(j1_int = '0') then
                    EA <= COUNT_J1;
                else
                    EA <= COUNT_J1a;
                end if;
            elsif(EA = COUNT_J2) then
                if(fim_j1 = '1') then
                    EA <= END_J1;
                elsif(j1_int = '1') then
                    EA <= COUNT_J2;
                else
                    EA <= COUNT_J1;
                end if;
            elsif(EA = COUNT_J2a) then
                if(j2_int = '0') then
                    EA <= COUNT_J2;
                else
                    EA <= COUNT_J2a;
                end if;
            elsif(EA = COUNT_J2) then
                if(fim_j2 = '1') then
                    EA <= END_J2;
                elsif(J2_int = '1') then
                    EA <= COUNT_J1;
                else
                    EA <= COUNT_J2;
                end if;
            end if;
        end if;
    end process meu_J1;

-- Sinais controlados pelo Estado Atual da maquina de estados - combinacional
led_j2  <= '1' when EA = COUNT_J2 else '0';
led_j1  <= '1' when EA = COUNT_J1 else '0';
conta   <= '1' when EA = COUNT_J1 or EA = COUNT_J2 else '0';
vez_j1  <= '0' when EA = COUNT_J1 else '1';
vez_j2  <= '0' when EA = COUNT_J2 else '1';
an      <= an_j1 when EA = COUNT_J1 else an_j2 when EA = COUNT_J2 else "1110";
dec_ddp <= dec_ddp_j1 when EA = COUNT_J1 else dec_ddp_j2 when EA = COUNT_J2 else "00000001";

end relogio_xadrez;



