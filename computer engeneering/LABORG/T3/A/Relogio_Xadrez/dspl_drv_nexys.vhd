library IEEE;
use IEEE.std_logic_1164.all;
use IEEE.std_logic_unsigned.all;

entity dspl_drv is
port (
     clock: in STD_LOGIC;
     reset: in STD_LOGIC;
     d4: in STD_LOGIC_VECTOR (5 downto 0);
     d3: in STD_LOGIC_VECTOR (5 downto 0);
     d2: in STD_LOGIC_VECTOR (5 downto 0);
     d1: in STD_LOGIC_VECTOR (5 downto 0);
     an: out STD_LOGIC_VECTOR (3 downto 0);
     dec_ddp: out STD_LOGIC_VECTOR (7 downto 0)
);
end dspl_drv;

--}} End of automatically maintained section

architecture dspl_drv of dspl_drv is
signal ck_1KHz: std_logic;
signal dig_selection: std_logic_vector (1 downto 0);
signal selected_dig: std_logic_vector (4 downto 0);

begin
-- 1KHz clock generation
process (reset, clock)
variable count_25K: integer range 0 to 25000;
begin
     if reset='1' then
         count_25K := 0;
         ck_1KHz <= '0';
     elsif (clock'event and clock='1') then
         count_25K := count_25K + 1;
         if (count_25K = 24999) then
             count_25K := 0;
             ck_1KHz <= not ck_1KHz;
         end if;
     end if;
end process;
-- 1KHz counter to select digit and register output
process (reset, ck_1KHz)
begin
     if reset='1' then
         dig_selection <= (others => '0');
         an <= (others => '1');                  -- Disable all displays
     elsif (ck_1KHz'event and ck_1KHz='1') then
         -- a 2-bit Johnson counter     
         dig_selection <= dig_selection(0) & not dig_selection (1);

         if dig_selection="00" then
             selected_dig <= d1(4 downto 0);
             an <= "111" & (not d1(5));
         elsif dig_selection="01" then
             selected_dig <= d2(4 downto 0);
             an <= "11" & (not d2(5)) & "1";
         elsif dig_selection="10" then
             selected_dig <= d3(4 downto 0);
             an <= "1" & (not d3(5)) & "11";
         else
             selected_dig <= d4(4 downto 0);
             an <= (not d4(5)) & "111";
         end if;
     end if;
end process;
-- digit 4-to-hex decoder
with selected_dig (4 downto 1) select
dec_ddp(7 downto 1) <=
     "0000001" when "0000", --0
     "1001111" when "0001", --1
     "0010010" when "0010", --2
     "0000110" when "0011", --3
     "1001100" when "0100", --4
     "0100100" when "0101", --5
     "0100000" when "0110", --6
     "0001111" when "0111", --7
     "0000000" when "1000", --8
     "0000100" when "1001", --9
     "0001000" when "1010", --A
     "1100000" when "1011", --b
     "0110001" when "1100", --C
     "1000010" when "1101", --d
     "0110000" when "1110", --E
     "0111000" when others; --F
-- and the decimal point
dec_ddp(0) <= selected_dig(0);

end dspl_drv;
