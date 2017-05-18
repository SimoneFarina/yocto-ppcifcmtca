
FILESEXTRAPATHS_append := ":${THISDIR}/openssh"

SRC_URI += " \
           file://0001-updated-sshkey.c-to-work-with-openssl-1.1.0.patch \
           file://0002-updated-rsa.-to-work-with-openssl-1.1.0.patch \
           file://0003-updated-ssh-ecdsa.c-to-work-with-openssl-1.1.0.patch \
           file://0004-updated-dh.-to-work-with-openssl-1.1.0.patch \
           file://0005-updated-digest-openssl.c-to-work-with-openssl-1.1.0.patch \
           file://0006-updated-ssh-rsa.c-to-work-with-openssl-1.1.0.patch \
           file://0007-updated-ssh-pkcs11.c-to-work-with-openssl-1.1.0.patch \
           file://0008-updated-kexdhs.c-to-work-with-openssl-1.1.0.patch \
           file://0009-updated-kexdhc.c-to-work-with-openssl-1.1.0.patch \
           file://0010-updated-kexgex.c-to-work-with-openssl-1.1.0.patch \
           file://0011-updated-kexgexs.c-to-work-with-openssl-1.1.0.patch \
           file://0012-updated-ssh-dss.c-to-work-with-openssl-1.1.0.patch \
           file://0013-updated-cipher.c-to-work-with-openssl-1.1.0.patch \
           file://0014-updated-monitor.c-to-work-with-openssl-1.1.0.patch \
           file://0015-updated-ssh-keygen.c-to-work-with-openssl-1.1.0.patch \
           file://0016-updated-ssh-pkcs11-client.c-to-work-with-openssl-1.1.patch \
           "
