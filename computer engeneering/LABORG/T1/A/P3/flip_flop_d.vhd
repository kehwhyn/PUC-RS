Library IEEE;
use IEEE.std_logic_1164.all;

entity ffd is
port(gi
	pre, crl, clk, d: in std_logic;
	q, qn: out std_logic
	);
end ffd;

architecture ffdd of ffd is
	signal q_int, qn_int: std_loc;
begin

	q_int <= '1' when (pre = '0') else
         	'0' when (crl = '0') else
          	D when (clk'event and clk = '1');
      	 
	q <= q_int;
	qn <= not(q_int);
    
end ffdd;
