SUMMARY = "Device Trees for BSPs"
DESCRIPTION = "Device Tree generation and packaging for BSP Device Trees."
SECTION = "bsp"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit deploy

INHIBIT_DEFAULT_DEPS = "1"
PACKAGE_ARCH = "${MACHINE_ARCH}"

DEPENDS += "dtc-native"

FILES_${PN} = "/boot/devicetree*"

#KERNEL_DTS_INCLUDE ??= ""

#DEVICETREE_FLAGS ?= "-R 8 -p 0x3000 \
#		-i ${WORKDIR}/devicetree \
#		${@' '.join(['-i %s' % i for i in d.getVar('KERNEL_DTS_INCLUDE', True).split()])} \
#		"
#DEVICETREE_PP_FLAGS ?= "-nostdinc -Ulinux \
#		-I${WORKDIR}/devicetree \
#		${@' '.join(['-I%s' % i for i in d.getVar('KERNEL_DTS_INCLUDE', True).split()])} \
#		-x assembler-with-cpp \
#		"

FILESEXTRAPATHS_append := "${THISDIR}/../../conf/machine/boards/ifc1410"

inherit xilinx-utils

# Using the MACHINE_DEVICETREE and MACHINE_KCONFIG vars, append them to SRC_URI
SRC_URI_append = "${@paths_affix(d.getVar("MACHINE_DEVICETREE", True) or '', prefix = 'file://')}"
#SRC_URI_append_ess-ifc1410-64b-rt = "${@paths_affix(d.getVar("MACHINE_DEVICETREE", True) or '', prefix = 'file://')}"


#python () {
#    # auto add dependency on kernel tree
#    if d.getVar("KERNEL_DTS_INCLUDE", True) != "":
#        d.setVarFlag("do_compile", "depends",
#            " ".join([d.getVarFlag("do_compile", "depends", True) or "", "virtual/kernel:do_shared_workdir"]))
#}

#do_compile() {
#	if test -n "${MACHINE_DEVICETREE}"; then
#		mkdir -p ${WORKDIR}/devicetree
#		for i in ${MACHINE_DEVICETREE}; do
#			if test -e ${WORKDIR}/$i; then
#				cp ${WORKDIR}/$i ${WORKDIR}/devicetree
#			fi
#		done
#	fi

#	for DTS_FILE in ${DEVICETREE}; do
#		DTS_NAME=`basename -s .dts ${DTS_FILE}`
#		${BUILD_CPP} ${DEVICETREE_PP_FLAGS} -o ${DTS_FILE}.pp ${DTS_FILE}
#		dtc -I dts -O dtb ${DEVICETREE_FLAGS} -o ${DTS_NAME}.dtb ${DTS_FILE}.pp
#	done
#}

#do_install() {
#	for DTS_FILE in ${DEVICETREE}; do
#		if [ ! -f ${DTS_FILE} ]; then
#			echo "Warning: ${DTS_FILE} is not available!"
#			continue
#		fi
#		DTS_NAME=`basename -s .dts ${DTS_FILE}`
#		install -d ${D}/boot/devicetree
#		install -m 0644 ${B}/${DTS_NAME}.dtb ${D}/boot/devicetree/${DTS_NAME}.dtb
#	done
#}

#do_deploy() {
#	for DTS_FILE in ${DEVICETREE}; do
#		if [ ! -f ${DTS_FILE} ]; then
#			echo "Warning: ${DTS_FILE} is not available!"
#			continue
#		fi
#		DTS_NAME=`basename -s .dts ${DTS_FILE}`
#		install -d ${DEPLOYDIR}
#		install -m 0644 ${B}/${DTS_NAME}.dtb ${DEPLOYDIR}/${DTS_NAME}.dtb
#	done
#}

#addtask deploy before do_build after do_install

