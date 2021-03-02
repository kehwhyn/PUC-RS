library IEEE;
	use IEEE.STD_LOGIC_1164.ALL;
	use IEEE.STD_LOGIC_UNSIGNED.ALL;

entity top is
port(
	a, b: in std_logic_vector(3 downto 0);
	clock, reset: in std_logic;
	Carry: out std_logic;
	an: out STD_LOGIC_VECTOR (3 downto 0);
	dec_ddp: out STD_LOGIC_VECTOR (7 downto 0)
	);
end top;

architecture tope of top is
	signal d_int1, d_int2, d_int3, d_int4: std_logic_vector(5 downto 0);
	signal soma: std_logic_vector(4 downto 0);
begin
    
	Somador_4: entity work.soma5
    	port map(
        	a => a, b => b, sum => soma);
   	 
	Decoder: entity work.dspl_drv
    	port map(
    	d1 => d_int1, d2 => d_int2, d3 => d_int3, d4 => d_int4, clock => clock, reset => reset, an => an, dec_ddp => dec_ddp);
	 
	Carry <= soma(4);   
	d_int1 <= '1' & a & '1';
	d_int2 <= '1' & b & '1';
	d_int3 <= "000000";
	d_int4 <= '1' & soma(3 downto 0) & '1';

end tope;