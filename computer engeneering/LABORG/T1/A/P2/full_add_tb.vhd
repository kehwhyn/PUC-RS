Library IEEE;
use IEEE.std_logic_1164.all;
use IEEE.std_logic_unsigned.all;

entity fulladd is
port(
	a, b: in std_logic_vector(3 downto 0);
	sum: out std_logic_vector(3 downto 0)
	);
end fulladd;

architecture fulladder of fulladd is
begin

	sum <= A + B;
    
end fulladder;
