library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;

entity soma5_tb is
end soma5_tb;

architecture somaa5_tb of soma5_tb is
	signal aa, bb, sum_int: std_logic_vector(4 downto 0);
begin
	UUT: entity work.soma5
	port map(
		a => aa, b => bb, sum => sum_int);

	aa <= “0000”, “0001” after 10 ns;
	bb <= “0011”, “0100”, after 20 ns;

end somaa5_tb;