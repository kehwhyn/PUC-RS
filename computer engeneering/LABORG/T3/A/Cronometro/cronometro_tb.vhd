----------------------------------------------------------------------------------
-- Testbench para Contador decrescente para o T5 de LABORG
-- Matheus / Moraes - PUCRS
-- 24/4/2015
----------------------------------------------------------------------------------
library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_ARITH.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;

entity tb_dec_counter is
end tb_dec_counter;

architecture tb_dec_counter of tb_dec_counter is

signal clock, reset, carga, conta : std_logic := '1';
signal chaves: std_logic_vector(6 downto 0);

begin

    clock <= not clock after 5 ns;
    reset <= '1', '0' after 73 ns;
    carga <= '0', '1' after 133 ns,  '0' after 425 ns;
    conta <= '0', '1' after 543 ns,  '0' after 925 ns;
    chaves <= "0000101";

    uut : entity work.dec_cron
    generic map ( CLOCK_FREQ => 4    --  para simulacao utilizar um divisor menor
    )
    port map (
        clk50   => clock,
        reset   => reset,
        carga    => carga,
        conta    => conta,
        chaves  => chaves,
        an        => open,
        dec_ddp => open
    );
    
end tb_dec_counter;
