library IEEE;
use IEEE.std_logic_1164.all;
use IEEE.numeric_std.all;

entity counter is
	generic (bits : positive := 4;        -- Size of the counter
			   modulo : positive := 10);    -- Counter modulo
	port (clock : in std_logic;
			reset : in std_logic := '0';
			enable : in std_logic := '1';
			count : out unsigned(bits-1 downto 0);
			carry : out std_logic);
begin
	assert modulo <= 2**bits
		report "Not enough bits for the size counter"
		severity error;		
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