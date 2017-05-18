SUMMARY = "A /dev/crypto device driver kernel module"

HOMEPAGE = "http://cryptodev-linux.org/"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

inherit module qoriq_build_64bit_kernel

PROVIDES += "cryptodev-module"

SRC_URI = "git://github.com/cryptodev-linux/cryptodev-linux \
           "
SRCREV = "87d959d9a279c055b361de8e730fab6a7144edd7"
S = "${WORKDIR}/git"

# Header file provided by a separate package
DEPENDS += "cryptodev-linux"

EXTRA_OEMAKE='KERNEL_DIR="${STAGING_KERNEL_DIR}" DESTDIR="${D}"'

do_configure[noexec] = "1"
do_compile[noexec] = "1"
do_install[noexec] = "1"
do_package[noexec] = "1"
do_packagedata[noexec] = "1"
do_package_write_rpm[noexec] = "1"
do_packagedata_setscene[noexec] = "1"


#This is the old recipe, it looks like the module is already provided so we do not need to comile it anymore

#   SUMMARY = "A /dev/crypto device driver kernel module"
#   
#   HOMEPAGE = "http://cryptodev-linux.org/"
#   
#   LICENSE = "GPLv2"
#   LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"
#   
#   inherit module qoriq_build_64bit_kernel
#   
#   PROVIDES += "cryptodev-module"
#   
#   SRC_URI = "git://github.com/cryptodev-linux/cryptodev-linux \
#              "
#   SRCREV = "87d959d9a279c055b361de8e730fab6a7144edd7"
#   
#   S = "${WORKDIR}/git"
#   
#   #FILESEXTRAPATHS_prepend := "${THISDIR}/files:\
#   #"
#   
#   # Header file provided by a separate package
#   DEPENDS += "cryptodev-linux"
#   
#   #SRC_URI_append = " \
#   #file://0001-Disable-installing-header-file-provided-by-another-p.patch \
#   #"
#   
#   EXTRA_OEMAKE='KERNEL_DIR="${STAGING_KERNEL_DIR}" DESTDIR="${D}"'
#   
#   #RCONFLICTS_${PN} = "ocf-linux"
#   #RREPLACES_${PN} = "ocf-linux"