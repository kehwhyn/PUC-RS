Library IEEE;
use IEEE.std_logic_1164.all;

entity ffd_tb is
end ffd_tb;

architecture ffdd_tb of ffd_tb is
	signal p, c, ck, dd, q_int, qn_int: std_logic;
begin

	UUT: entity work.ffd
            	port map(
                	pre => p, crl => c, clk => ck, d => dd, q => q_int, qn => qn_int);
               	 
	ck <= '0', '1' after 5 ns, '0' after 10 ns, '1' after 15 ns, '0' after 20 ns, '1' after 25 ns, '0' after 30 ns, '1' after 35 ns, '0' after 40 ns, '1' after 45 ns, '0' after 50 ns;
	dd <= '1', '0' after 6 ns, '1' after 9 ns, '0' after 14 ns, '1' after 21 ns, '0' after 26 ns, '1' after 31 ns, '0' after 34 ns, '1' after 41 ns, '0' after 44 ns;
	p <= '0' after 17 ns, '1' after 19 ns, '0' after 37 ns, '1' after 39 ns;
     c <= '0' after 7 ns, '1' after 9 ns, '0' after 22 ns, '1' after 24 ns;
end ffdd_tb;
