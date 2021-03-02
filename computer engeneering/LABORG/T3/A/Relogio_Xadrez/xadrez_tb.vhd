library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_ARITH.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;

entity tb is
end tb;

architecture tb of tb is

signal clock, reset, carga, j1, j2 : std_logic := '1';
signal chaves: std_logic_vector(6 downto 0);

begin

clock <= not clock after 5 ns;
reset <= '1', '0' after 73 ns;
carga <= '0', '1' after 133 ns, '0' after 425 ns;
chaves <= "0000101";

j1 <= '0', '1' after 12002 ns, '0' after 12100 ns,
    '1' after 30002 ns, '0' after 30100 ns;

j2 <= '0', '1' after 402 ns, '0' after 500 ns,
    '1' after 6002 ns, '0' after 6100 ns,
    '1' after 25002 ns, '0' after 25100 ns;


uut : entity work.relogio_xadrez
generic map ( CLOCK_FREQ => 4    -- para simulacao utilizar um divisor menor
    )
    port map (
    clock => clock,
    reset => reset,
    carga => carga,
    j1     => j1,
    j2     => j2,
    chaves => chaves,
    an     => open,
    dec_ddp => open
    );
end tb;
