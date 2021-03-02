library IEEE;
use IEEE.std_logic_1164.all;

entity top_conts is
	port(
		ck, reset, selCPU: in std_logic;
		an: out std_logic_vector(3 downto 0);
		dec_ddp: out std_logic_vector(7 downto 0)
		);
end top_conts;

architecture top of top_conts is
	signal ce, resetCPU, rw: std_logic;
	signal addressCPU, data_outCPU, addressSERIAL: std_logic_vector(31 downto 0);
	signal an_int: std_logic_vector(3 downto 0);
	signal dec_ddp_int: std_logic_vector(7 downto 0);
begin

	MIPS_MULT: entity work.MIPSmulti_with_BRAMs
		port map(
			clock => ck, reset => reset, selCPU => selCPU,
			ce => ce, resetCPU => resetCPU, rw => rw,
			addressCPU => addressCPU, addressSERIAL => addressSERIAL, data_outCPU => data_outCPU);
			
	Periferico: entity work.periferico
		port map(
			address => addressCPU, data => data_outCPU(3 downto 0),
			ce => ce, ck => ck, rst => resetCPU, rw => rw,
			an => an, dec_ddp => dec_ddp);

end top;