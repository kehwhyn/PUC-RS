library IEEE;
use IEEE.std_logic_1164.all;

entity halfadd is
port(
    	a, b: in std_logic;
    	sum, carry: out std_logic
	);
end halfadd;

architecture halfadder of halfadd is
begin

	sum <= a xor b;
	carry <= a and b;
    
end halfadder;