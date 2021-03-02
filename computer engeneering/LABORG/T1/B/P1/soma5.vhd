library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;

entity soma5 is
port(
	a, b: in std_logic_vector(3 downto 0);
	sum: out std_logic_vector(4 downto 0)
	);
end soma5;

architecture somaa5 of soma5 is
	signal aa, bb: std_logic_vector(4 downto 0);
begin
    
	aa <= '0' & a;
	bb <= '0' & b;
   	 sum <= sum_int;

end somaa5;