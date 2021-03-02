Library IEEE;
	use IEEE.STD_LOGIC_1164.ALL;
    
entity prim_proc_tb is
end prim_proc_tb;

architecture prim_procc_tb of prim_proc_tb is
	signal in1_int, in2_int, in3_int, in4_int, sai_int: std_logic;
     signal ctrl_int: std_logic_vector(1 downto 0);
begin

	PEPPA: entity work.prim_proc
    	port map(in1 => in1_int, in2 => in2_int, in3 => in3_int,
in4 => in4_int, ctrl => ctrl_int, sai => sai_int);

	ctrl_int <= "00", "01" after 10 ns, "10" after 20 ns, "11" after 30 ns;
     in1_int <= '1', '0' after 10 ns, '1' after 20 ns, '0' after 30 ns;
     in2_int <= '1', '0' after 10 ns, '1' after 20 ns, '0' after 30 ns;
     in3_int <= '1', '0' after 10 ns, '1' after 20 ns, '0' after 30 ns;
     in4_int <= '0', '1' after 10 ns, '0' after 20 ns, '1' after 35 ns;
    
end prim_procc_tb;