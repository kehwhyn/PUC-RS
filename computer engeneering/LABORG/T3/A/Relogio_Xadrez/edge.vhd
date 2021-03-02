library ieee;
use ieee.std_logic_unsigned.all;
use ieee.std_logic_1164.all;
library work;

entity edge_detector is
        port(   clock           : in    std_logic;
                reset           : in    std_logic;
                din             : in    std_logic;              -- Input data
                rising          : out   std_logic               -- Edge detected
        );
end edge_detector;

architecture edge_detector of edge_detector is
        signal din_reg          : std_logic;
begin

   rising <= '1' when din_reg = '0' and din = '1' else '0';

   process (clock,reset)
   begin
          if reset='1' then
                 din_reg <= '0';
          elsif clock'event and clock='1' then
                  din_reg <= din;
          end if;
   end process;

end edge_detector;
