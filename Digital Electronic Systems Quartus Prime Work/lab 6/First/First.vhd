library IEEE;
use IEEE.std_logic_1164.all;

entity First is
port (
x1, x2, x3 : in std_logic;
f : out std_logic
 -- hello
);
end entity First;

architecture comb of First is
	signal andx12 : std_logic;
	signal andx23 : std_logic;	
begin
	andx12 <= x1 and x2;
	andx23 <= not x2 and x3;
	f <= andx12 or andx23;
end architecture comb;
