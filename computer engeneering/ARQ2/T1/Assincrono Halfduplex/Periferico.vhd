library IEEE;
use IEEE.STD_LOGIC_1164.all;
use IEEE.STD_LOGIC_unsigned.all;

entity Periferico is
	port
	(
		clock: 			in STD_LOGIC;
		reset: 			in STD_LOGIC;
		
		-- Dado para comunicacao bidirecional
		data: 			inout STD_LOGIC_VECTOR(7 downto 0);
		
		-- Interface com a interface de comunicacao
			
			-- Sinais de transmicao de dados
			send_CPU: out STD_LOGIC;
			ack_CPU: 	in STD_LOGIC;
			
			-- Sinal de recepcao de dados 
			receive_CPU:	in STD_LOGIC
	);
end Periferico;

architecture Periferico of Periferico is
	
	signal dado: 		STD_LOGIC_VECTOR(7 downto 0);
	signal contador: 	STD_LOGIC_VECTOR(7 downto 0);
	signal flag: std_logic;

begin
	
	data <= dado when flag = '1' else (others=>'Z') after 25 ns;
	
	Recepcao: process(clock, reset)
	begin
		
		if reset = '1' then
		
			flag <= '0';
			dado <= (others=>'Z');
			contador <= (others=>'0');
			send_CPU <= '0';
		
		elsif falling_edge(clock)  then
			
			-- OBS.: O sinal send deve ficar apenas um ciclo em '1'
	
			if ack_CPU = '1' and contador < 2 then
			
				contador <= contador + 1;
				dado <= contador;
				send_CPU <= '1';
				flag <= '1';
			else
					send_CPU <= '0';
				flag <= '0';
				
			end if;
		
				
			if receive_CPU = '1' then
					dado <= data;
				
				if dado > 0 and contador > 0 then
					contador <= (others=>'0');
					send_CPU <= '1';
					flag <= '1';
				end if;
			end if;
				
			if contador > 0 then 
				flag <= '0';
				end if;
		end if;
	end process;
end Periferico;
