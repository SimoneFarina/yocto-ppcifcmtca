#DESCRIPTION = "Linux kernel for Freescale platforms"
#SECTION = "kernel"
#LICENSE = "GPLv2"
#LIC_FILES_CHKSUM = ""

FILESEXTRAPATHS_append := ":${THISDIR}/files"
#FILESEXTRAPATHS_prepend_ess-ifc1410-64b := "${THISDIR}/defconfig64:"
#FILESEXTRAPATHS_prepend_ess-ifc1410-32b := "${THISDIR}/defconfig32:"


#SRC_URI_ess-ifc1410-64b += "file://ess-ifc1410-64b.cfg \
#"
#SRC_URI_ess-ifc1410-64b += "file://defconfig \
#"
SRC_URI_append_ess-ifc1410-64b = "file://ess-ifc1410-64b.patch;patch=1 \
"

#SRC_URI_ess-ifc1410-32b += "file://ess-ifc1410-32b.cfg \
#"
#SRC_URI_ess-ifc1410-32b += "file://defconfig \
#"
#SRC_URI_append_ess-ifc1410-32b = "file://ess-ifc1410-32b.patch \
#"