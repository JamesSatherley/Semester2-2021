-- Copyright (C) 2020  Intel Corporation. All rights reserved.
-- Your use of Intel Corporation's design tools, logic functions 
-- and other software and tools, and any partner logic 
-- functions, and any output files from any of the foregoing 
-- (including device programming or simulation files), and any 
-- associated documentation or information are expressly subject 
-- to the terms and conditions of the Intel Program License 
-- Subscription Agreement, the Intel Quartus Prime License Agreement,
-- the Intel FPGA IP License Agreement, or other applicable license
-- agreement, including, without limitation, that your use is for
-- the sole purpose of programming logic devices manufactured by
-- Intel and sold by Intel or its authorized distributors.  Please
-- refer to the applicable agreement for further details, at
-- https://fpgasoftware.intel.com/eula.

-- ***************************************************************************
-- This file contains a Vhdl test bench template that is freely editable to   
-- suit user's needs .Comments are provided in each section to help the user  
-- fill out necessary details.                                                
-- ***************************************************************************
-- Generated on "09/09/2021 09:58:51"
                                                            
-- Vhdl Test Bench template for design  :  First
-- 
-- Simulation tool : ModelSim-Altera (VHDL)
-- 

LIBRARY ieee;                                               
USE ieee.std_logic_1164.all;                                

ENTITY First_vhd_tst IS
END First_vhd_tst;
ARCHITECTURE First_arch OF First_vhd_tst IS
-- constants                                                 
-- signals                                                   
SIGNAL f : STD_LOGIC;
SIGNAL x1 : STD_LOGIC;
SIGNAL x2 : STD_LOGIC;
SIGNAL x3 : STD_LOGIC;
COMPONENT First
	PORT (
	f : OUT STD_LOGIC;
	x1 : IN STD_LOGIC;
	x2 : IN STD_LOGIC;
	x3 : IN STD_LOGIC
	);
END COMPONENT;
BEGIN
	i1 : First
	PORT MAP (
-- list connections between master ports and signals
	f => f,
	x1 => x1,
	x2 => x2,
	x3 => x3
	);
init : PROCESS                                               
-- variable declarations                                     
BEGIN  

	x1 <= '0'; x2 <= '0'; x3 <= '0';
	wait for 100 ns;
	x1 <= '1';
	wait for 100 ns;
	x1 <= '0'; x2 <= '1';
	wait for 100 ns;
	x1 <= '1';
	wait for 100 ns;
	x1 <= '0'; x2 <= '0'; x3 <= '1';
	wait for 100 ns;
	x1 <= '1';
	wait for 100 ns;
	x1 <= '0'; x2 <= '1';
	wait for 100 ns;
	x1 <= '1';
	wait for 100 ns;
                                                      
        -- code that executes only once                      
WAIT;                                                       
END PROCESS init;                                           
always : PROCESS                                              
-- optional sensitivity list                                  
-- (        )                                                 
-- variable declarations                                      
BEGIN                                                         
        -- code executes for every event on sensitivity list  
WAIT;                                                        
END PROCESS always;                                          
END First_arch;
