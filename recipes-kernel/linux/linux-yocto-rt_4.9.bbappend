
#COMPATIBLE_MACHINE = "(ess-ifc1410-64b|ess-ifc1410-32b)"

KERNEL_DEVICETREE_ess-ifc1410-64b = "${MACHINE}.dtb"
KERNEL_DEVICETREE_ess-ifc1410-64b-rt = "${MACHINE}.dtb"

KERNEL_DEVICETREE_ess-ifc1410-32b = "${MACHINE}.dtb"
KERNEL_DEVICETREE_ess-ifc1410-32b-rt = "${MACHINE}.dtb"

FILESEXTRAPATHS_prepend_ess-ifc1410-64b-rt := "${THISDIR}/files64:"
SRC_URI += "file://fragment.cfg"
#SRC_URI += "file://defconfig"

# Functionality flags
#KERNEL_EXTRA_FEATURES ?= " "
#KERNEL_FEATURES_append = " ${KERNEL_EXTRA_FEATURES}"
#KERNEL_FEATURES_append_ess-ifc1410-64b = " "
#KERNEL_FEATURES_append_ess-ifc1410-32b = " "
