library IEEE;
use IEEE.std_logic_1164.all;

entity halfadd_tb is
end halfadd_tb;

architecture halfadder_tb of halfadd_tb is
   signal aa, bb, som, curry: std_logic;
begin

	UUT: entity work.halfadd
        	port map(
                   	a => aa, b => bb, sum => som, carry => curry);
                  	 
	aa <= '0', '1' after 10 ns, '0' after 20 ns, '1' after 30 ns;
	bb <= '0', '1' after 20 ns;
    
end halfadder_tb;