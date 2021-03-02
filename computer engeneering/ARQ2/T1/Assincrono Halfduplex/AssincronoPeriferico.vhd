library IEEE;
use IEEE.std_logic_1164.all;

package definicoesTransmissaoAssincronaPER is
	type ESTADO_TRANSMISSAO is (esperaDados, esperaAck, esperaFimAck);
end definicoesTransmissaoAssincronaPER;

package definicoesRecepcaoAssincronaPER is
	type ESTADO_RECEPCAO is (receberDados, receberFim);
end definicoesRecepcaoAssincronaPER;

library IEEE;
use IEEE.STD_LOGIC_1164.all;
use work.definicoesTransmissaoAssincronaPER.all;
use work.definicoesRecepcaoAssincronaPER.all;

entity InterfacePeriferico is
	port
	(
		clock: 			in STD_LOGIC;
		reset: 			in STD_LOGIC;
		
		data: 			inout STD_LOGIC_VECTOR(7 downto 0);
		
		-- Interface de comunicacao assincrona com a interface do CPU
		receive_in_PER: 	in STD_LOGIC;
		accept_CPU: 		out STD_LOGIC;
		send_out_CPU: 		out STD_LOGIC;
		ack_in_PER: 		in STD_LOGIC;
		
		-- Interface com o Periférico
		receive_out_PER: 	out STD_LOGIC;
		send_in_CPU: 		in STD_LOGIC;
		ack_out_PER: 		out STD_LOGIC
	);
end InterfacePeriferico;

architecture InterfacePeriferico of InterfacePeriferico is

	signal manda, recebe : std_logic;
	signal estadoTx: 	ESTADO_TRANSMISSAO;
	signal estadoRx: 	ESTADO_RECEPCAO;
	signal regData_trans: 	STD_LOGIC_VECTOR(7 downto 0);
	signal regData_rec: 	STD_LOGIC_VECTOR(7 downto 0);
	
begin

	data <= regData_trans when manda = '1' and recebe = '0' else regData_rec when recebe = '1' and manda = '0' else (others=>'Z') after 25 ns;

	Transmissao: process(clock, reset)
	begin
		if reset = '1' then
		
			manda <= '0';
			estadoTx <= esperaDados;
			ack_out_PER <= '0';
   	 	send_out_CPU <= '0';
			regData_trans <= (others=>'0');
			
		elsif rising_edge(clock) then
			
			case estadoTx is
				
				when esperaDados =>
					
					if regData_trans = "0000001" and ack_in_PER = '0' then
						regData_trans <= (others => '0');
						ack_out_PER <= '1';
						recebe <= '1';
					end if;
					
					if manda = '0' and recebe = '0' then 
					ack_out_PER <= '0';
					end if;
					
					if send_in_CPU = '1' then
					
						regData_trans <= data;
						send_out_CPU <= '1';
						manda <= '1';
						estadoTx <= esperaAck;
					end if;

				when esperaAck =>
					
					if ack_in_PER = '1' then
						
						estadoTx <= esperaFimAck;
						manda <= '0';
						send_out_CPU <= '0';
					
					end if;

				when esperaFimAck =>
					
					if ack_in_PER = '0' then
						
						ack_out_PER <= '1';
						estadoTx <= esperaDados;
						
					end if;

				when others => null;
			end case;
		end if;
		if reset = '1' then
			recebe <= '1';
			receive_out_PER <= '0';
			accept_CPU <= '1';
			regData_rec <= (others=>'Z');
			
		elsif rising_edge(clock) then
				
				case estadoRx is
				
					when receberDados =>
						
						if regData_rec = "00000100" then
							regData_rec <= (others => '0');
								ack_out_PER <= '1';
								manda <= '0';
								recebe <= '0';
								end if;
						 
						if receive_in_PER = '1' then
							
							recebe <='0';
							regData_rec <= data;
							manda <= '0';
							recebe <='1';
							
							accept_CPU <= '1';
							receive_out_PER <= '1';
							estadoRx <= receberFim;
							
						end if;
						
					when receberFim =>
							
						if receive_in_PER = '0' then
							recebe <='0';
							accept_CPU <= '0';
							receive_out_PER <= '0';
							estadoRx <= receberDados;
						
						end if;
					when others => null;
				end case;
		end if;
	end process;
	
end InterfacePeriferico;
