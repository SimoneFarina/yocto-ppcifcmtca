#require cryptodev-qoriq_${PV}.inc
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

SUMMARY = "A /dev/crypto device driver header file"

SRC_URI = "git://github.com/cryptodev-linux/cryptodev-linux \
           "
SRCREV = "87d959d9a279c055b361de8e730fab6a7144edd7"

PROVIDES += "cryptodev-linux"

do_compile[noexec] = "1"

#S = "${WORKDIR}/cryptodev-linux-${PV}"
S = "${WORKDIR}/git"

# Just install cryptodev.h which is the only header file needed to be exported
do_install() {
	echo "executing command: install -D ${S}/crypto/cryptodev.h ${D}${includedir}/crypto/cryptodev.h"
	install -D ${S}/crypto/cryptodev.h ${D}${includedir}/crypto/cryptodev.h
}

ALLOW_EMPTY_${PN} = "1"
