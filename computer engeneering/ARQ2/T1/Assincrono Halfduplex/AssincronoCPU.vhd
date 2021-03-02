library IEEE;
use IEEE.std_logic_1164.all;

package definicoesTransmissaoAssincronaCPU is
	type ESTADO_TRANSMISSAO is (esperaDados, esperaAck, esperaFimAck);
end definicoesTransmissaoAssincronaCPU;

package definicoesRecepcaoAssincronaCPU is
	type ESTADO_RECEPCAO is (receberDados, receberFim);
end definicoesRecepcaoAssincronaCPU;

library IEEE;
use IEEE.STD_LOGIC_1164.all;
use work.definicoesTransmissaoAssincronaCPU.all;
use work.definicoesRecepcaoAssincronaCPU.all;

entity InterfaceCPU is
	port
	(
		clock: 			in STD_LOGIC;
		reset: 			in STD_LOGIC;
		
		data: 			inout STD_LOGIC_VECTOR(7 downto 0);
		
		-- Interface de comunicacao assincrona com a interface do periférico
		send_out_PER: 		out STD_LOGIC;
		receive_in_CPU: 	in STD_LOGIC;
		ack_in_CPU: 		in STD_LOGIC;
		accept_PER: 		out STD_LOGIC;
		
		-- Interface com o CPU
		send_in_PER: 		in STD_LOGIC;
		receive_out_CPU: 	out STD_LOGIC;
		ack_out_CPU: 		out STD_LOGIC
	);
end InterfaceCPU;

architecture InterfaceCPU of InterfaceCPU is

	signal estadoTx: 	ESTADO_TRANSMISSAO;
	signal estadoRx: 	ESTADO_RECEPCAO;
	signal manda, recebe: 	std_logic;
	signal regData_trans: 	STD_LOGIC_VECTOR(7 downto 0);
	signal regData_rec: 	STD_LOGIC_VECTOR(7 downto 0);
	
begin
	
	data <= regData_trans when manda = '1' and recebe = '0' else regData_rec when recebe = '1' and manda = '0' else(others=>'Z') after 25 ns;

	Transmissao: process(clock, reset)
	begin
		if reset = '1' then
		
			manda <= '1';
			estadoTx <= esperaDados;
			ack_out_CPU <= '0';
   	 	send_out_PER <= '0';
			regData_trans <= (others=>'0');
			
		elsif falling_edge(clock) then
			
			case estadoTx is
				
				when esperaDados =>
				
					if regData_trans = "00000101" and ack_in_CPU = '0' then
						regData_trans <= (others => '0');
						ack_out_CPU <= '1';
						recebe <= '1';
						manda <= '1';
					end if;
					
					if manda = '0' then
					ack_out_CPU <= '0'; 
					end if;
					
					if send_in_PER = '1' then
					
						regData_trans <= data;
						send_out_PER <= '1';
						manda <= '1';
						estadoTx <= esperaAck;
					end if;

				when esperaAck =>
					
					if ack_in_CPU = '1' then
						
						estadoTx <= esperaFimAck;
						manda <= '0';
						send_out_PER <= '0';
					
					end if;

				when esperaFimAck =>
					
					if ack_in_CPU = '0' then
						
						ack_out_CPU <= '1';
						estadoTx <= esperaDados;
						
					end if;

				when others => null;
			end case;
		end if;	
		if reset = '1' then
			recebe <= '0';
			receive_out_CPU <= '0';
			accept_PER <= '0';
			regData_rec <= (others=>'0');
			
		elsif rising_edge(clock) then
				
				case estadoRx is
				
					when receberDados =>
					
						if regData_rec = "0000001" then
							regData_rec <= (others => '0');
								ack_out_CPU <= '1';
								manda <= '0';
								end if;
								
						if receive_in_CPU = '1' then
							
							recebe <='0';
							regData_rec <= data;
							manda <= '0';
							recebe <='1';
							
							accept_PER <= '1';
							receive_out_CPU <= '1';
							estadoRx <= receberFim;
							
						end if;
					when receberFim =>
							
						if receive_in_CPU = '0' then
							recebe <='0';
							accept_PER <= '0';
							receive_out_CPU <= '0';
							estadoRx <= receberDados;
						
						end if;
					when others => null;
				end case;
		end if;
	end process;
	
end InterfaceCPU;
