library IEEE;
use IEEE.STD_LOGIC_1164.all;
use IEEE.STD_LOGIC_unsigned.all;

entity CPU is
	port
	(
		clock: 				in STD_LOGIC;
		reset: 				in STD_LOGIC;
		
		-- Dado para comunicacao bidirecional
		data: 				inout STD_LOGIC_VECTOR(7 downto 0);
		
		-- Interface com a interface de comunicacao
			
			-- Sinais de transmicao de dados
			send_PER: 		out STD_LOGIC;
			ack_PER: 		in STD_LOGIC;
	
			-- Sinal de recepcao de dados
			receive_PER: 	in STD_LOGIC
	);
end CPU;

architecture CPU of CPU is

	signal dado: 		STD_LOGIC_VECTOR(7 downto 0);
	signal contador: 	STD_LOGIC_VECTOR(7 downto 0);
	signal flag: std_logic;

begin

	data <= dado when flag = '1' else (others=>'Z') after 25 ns;

	Transmissao: process(clock, reset)
	begin
	
		if reset = '1' then

			flag <= '0';
			dado <= (others=>'0');
			contador <= (others=>'0');
			send_PER <= '1';

		elsif falling_edge(clock) then

			if ack_PER = '1' and contador < 5 then
			
				contador <= contador + 1;
				dado <= contador;
				send_PER <= '1';
				flag <= '1';
			else
				send_PER <= '0';
				flag <= '0';
				
			end if;
		
				
			if receive_PER = '1' then
					dado <= data;
				if data > 0 and contador > 4 then
					contador <= (others=>'0');
				end if;
			end if;
				
			if contador > 4 then 
				flag <= '0';
				end if;
		end if;
	end process;
end CPU;
