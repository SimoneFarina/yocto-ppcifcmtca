#require recipes-kernel/linux/linux-dtb.inc


# make everything compatible for the time being
COMPATIBLE_MACHINE_$MACHINE = "$MACHINE"
#COMPATIBLE_MACHINE = "(ess-ifc1410-64b|ess-ifc1410-32b)"

KERNEL_DEVICETREE_$MACHINE = "${MACHINE}.dtb"
#KERNEL_DEVICETREE_ess-ifc1410-64b = "${MACHINE}.dtb"
#KERNEL_DEVICETREE_ess-ifc1410-64b-rt = "${MACHINE}.dtb"
#KERNEL_DEVICETREE_ess-ifc1410-32b = "${MACHINE}.dtb"
#KERNEL_DEVICETREE_ess-ifc1410-32b-rt = "${MACHINE}.dtb"

FILESEXTRAPATHS_prepend_ess-ifc1410-64b-rt := "${THISDIR}/files64:"
SRC_URI_append_ess-ifc1410-64b-rt = " file://fragment.cfg "
#SRC_URI += "file://defconfig "

FILESEXTRAPATHS_append := ":${THISDIR}/../../conf/machine/boards/ifc1410:${THISDIR}/files:"

SRC_URI_append = " file://0001-fixed-issue-with-crypto-when-compiling-for-kernel-ve.patch \
				"

inherit xilinx-utils
# Using the MACHINE_DEVICETREE and MACHINE_KCONFIG vars, append them to SRC_URI
#SRC_URI_append = "${@paths_affix(d.getVar("MACHINE_DEVICETREE", True) or '', prefix = 'file://')}"
SRC_URI_append = "${@paths_affix(d.getVar("KERNEL_DEVICETREE", True) or '', prefix = 'file://')}"
#SRC_URI_append_ess-ifc1410-64b-rt = "${@paths_affix(d.getVar("MACHINE_DEVICETREE", True) or '', prefix = 'file://')}"

# Functionality flags
#KERNEL_EXTRA_FEATURES ?= " "
#KERNEL_FEATURES_append = " ${KERNEL_EXTRA_FEATURES}"
#KERNEL_FEATURES_append_ess-ifc1410-64b = " "
#KERNEL_FEATURES_append_ess-ifc1410-32b = " "