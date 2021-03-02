library IEEE;
use IEEE.std_logic_1164.all;

entity periferico is
	port(
		ck, rst, ce, rw: in std_logic;
		address: in std_logic_vector(31 downto 0);
		data: in std_logic_vector(3 downto 0);
		an: out std_logic_vector(3 downto 0);
		dec_ddp: out std_logic_vector(7 downto 0)
		);
end periferico;

architecture peri of periferico is
	signal d1, d2, d3, d4: std_logic_vector(5 downto 0);
	begin

    flor: process(ck)
    begin
	 if(ck'event and ck = '1') then
			if(rst = '0') then
				 if(x"10008000" = address and (ce = '1' and rw = '0')) then
					  d1 <= '1' & data & '1';
				 elsif(x"10008001" = address and (ce = '1' and rw = '0')) then
					  d2 <= '1' & data & '1';
				 elsif(x"10008002" = address and (ce = '1' and rw = '0')) then
					  d3 <= '1' & data & '1';
				 elsif(x"10008003" = address and (ce = '1' and rw = '0')) then
					  d4 <= '1' & data & '1';
				 end if;
			else
				 d1 <= '1' & "0000" & '1';
				 d2 <= '1' & "0000" & '1';
				 d3 <= '1' & "0000" & '1';    
				 d4 <= '1' & "0000" & '1';
			end if;
	 end if;
    end process flor;

	display: entity work.dspl_drv
		port map(
			clock => ck, reset => rst, d1 => d1, d2 => d2, d3 => d3,
			d4 => d4, an => an, dec_ddp => dec_ddp);

end peri;