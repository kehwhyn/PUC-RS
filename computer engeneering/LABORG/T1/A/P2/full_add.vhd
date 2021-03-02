Library IEEE;
use IEEE.std_logic_1164.all;
use IEEE.std_logic_unsigned.all;

entity fulladd_tb is
end fulladd_tb;

architecture fulladder_tb of fulladd_tb is
	signal aa, bb, som: std_logic_vector(3 downto 0);
begin

   UUT: entity work.fulladd
    	port map(
            	a => aa, b => bb, sum => som);
           	 
	aa <= x"9", x"5" after 10 ns, x"A" after 20 ns, x"B" after 30 ns;
	bb <= x"8", x"3" after 20 ns;
    
end fulladder_tb;
