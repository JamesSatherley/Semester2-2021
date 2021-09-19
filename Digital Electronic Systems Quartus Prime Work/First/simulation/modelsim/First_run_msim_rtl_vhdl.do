transcript on
if {[file exists rtl_work]} {
	vdel -lib rtl_work -all
}
vlib rtl_work
vmap work rtl_work

vcom -93 -work work {C:/Users/James - Desktop/Desktop/uni/2021/Semester2-2021/Digital Electronic Systems Quartus Prime Work/First.vhd}

vcom -93 -work work {C:/Users/James - Desktop/Desktop/uni/2021/Semester2-2021/Digital Electronic Systems Quartus Prime Work/simulation/modelsim/First.vht}

vsim -t 1ps -L altera -L lpm -L sgate -L altera_mf -L altera_lnsim -L cycloneive -L rtl_work -L work -voptargs="+acc"  First_vhd_tst

add wave *
view structure
view signals
run -all
