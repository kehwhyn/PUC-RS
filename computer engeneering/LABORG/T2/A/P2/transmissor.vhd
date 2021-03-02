Library IEEE;
Use IEEE.std_logic_1164.all;

Entity Transmissor is
Port(
	clk, reset, send: in std_logic;
	palavra: in std_logic_vector(7 downto 0);
	Linha: out std_logic;
	Busy: out std_logic
	);
End Transmissor;

Architecture TX of Transmissor is
	signal cont: integer := 0;
	signal n: integer:= 7;
Begin

	process(clk, reset)
    	begin
        	if(reset = '0') then
            	if(clk'event and clk = '1') then
                	if(cont = 0 or cont = 11) then
                        	Busy <= '0';
                        	Linha <= '1';
                        	cont <= 0;
                        	n <= 7;
                    	if(send = '1') then
                        	cont <= cont + 1;
                    	end if;
                 	elsif(cont = 1 or cont = 10) then --certo
                     	Busy <= '1';
                     	Linha <= '0';
                     	cont <=  cont + 1;
                 	else
                     	Busy <= '1';
                     	Linha <= palavra(n);
                     	n <= n - 1;
                     	cont <= cont + 1;
                 	end if;
            	end if;
    	else
        	Busy <= '0';
        	Linha <= '1';
    	end if;
	end process;
end TX;