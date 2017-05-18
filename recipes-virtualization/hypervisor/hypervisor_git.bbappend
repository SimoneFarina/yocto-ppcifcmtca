#EXTRA_OEMAKE_append = 'OLD_PROJECTVERSION=$(strip $(shell cat $(O)version))' 

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI_append = " \
	file://0001-fixed-OLD_PROJECTVERSION-issue-for-hypervisor.patch \
	file://0001-changed-HOSTCC-from-cc-to-gcc.patch \
	"

#do_preconfigure() {
#	mkdir -p ${S}/${OUTPUT}
#	echo "adding version file in ${S}/${OUTPUT}"
#	echo "0" > ${S}/${OUTPUT}/version
#}
#addtask preconfigure before do_configure


#do_configure_append () {
#	export HOSTCC=gcc
#}
