library IEEE;
use IEEE.std_logic_1164.all;
use IEEE.numeric_std.all;

entity counter is
    port (clock : in std_logic;
        count : out unsigned(3 downto 0) );
end counter;

architecture cntr of counter is
    signal i_count : unsigned(3 downto 0) := (others => '0'); -- Internal counter
begin
    process (clock) is
    begin
        if rising_edge(clock) then
            i_count <= i_count + 1; -- Increment the counter every clock cycle
        end if;
    end process;

    count <= i_count; -- Connect internal counter register to count output port
end architecture cntr;