require recipes-bsp/u-boot/u-boot.inc
#inherit fsl-u-boot-localversion

PROVIDES += "u-boot"
HOMEPAGE = "http://www.denx.de/wiki/U-Boot/WebHome"
SECTION = "bootloaders"

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=a2c678cfd4a4d97135585cad908541c6"
PE = "1"

INHIBIT_DEFAULT_DEPS = "1"
DEPENDS = "libgcc virtual/${TARGET_PREFIX}gcc"
DEPENDS_append_ess-ifc1410 = " boot-format-native"

SRC_URI = "git://git.denx.de/u-boot.git;commit=fe84c48eeb8e9cb0b8b80a4c0a53bb089adff9af \
"
SRCREV = "fe84c48eeb8e9cb0b8b80a4c0a53bb089adff9af"

S = "${WORKDIR}/git"

python () {
    if d.getVar("TCMODE", True) == "external-fsl":
        return

    ml = d.getVar("MULTILIB_VARIANTS", True)
    arch = d.getVar("OVERRIDES", True)
    if "e5500-64b:" in arch or "e6500-64b:" in arch:
        if not "lib32" in ml:
            raise bb.parse.SkipPackage("Building the u-boot for this arch requires multilib to be enabled")
        sys_multilib = 'powerpc' + d.getVar('TARGET_VENDOR', False) + 'mllib32-' + d.getVar('HOST_OS', False)
#        d.setVar('DEPENDS_append', ' lib32-gcc-cross-powerpc lib32-libgcc')
        d.setVar('PATH_append', ':' + d.getVar('STAGING_BINDIR_NATIVE', False) + '/' + sys_multilib)
        d.setVar('TOOLCHAIN_OPTIONS_append', '/../lib32-' + d.getVar("MACHINE", False))
        d.setVar("WRAP_TARGET_PREFIX", sys_multilib + '-')
}

WRAP_TARGET_PREFIX ?= "${TARGET_PREFIX}"
EXTRA_OEMAKE = 'CROSS_COMPILE=${WRAP_TARGET_PREFIX} CC="${WRAP_TARGET_PREFIX}gcc ${TOOLCHAIN_OPTIONS}" V=1'
EXTRA_OEMAKE += 'HOSTCC="${BUILD_CC} ${BUILD_CFLAGS} ${BUILD_LDFLAGS}"'

LOCALVERSION = "+fsl"

do_compile_append_ess-ifc1410 () {
    unset i j
    if [ -n "${UBOOT_CONFIG}" ];then
        for config in ${UBOOT_MACHINE}; do
            i=`expr $i + 1`;
            for type in ${UBOOT_CONFIG}; do
                j=`expr $j + 1`;
                if [ $j -eq $i ]; then
                    # use boot-format to regenerate spi image if BOOTFORMAT_CONFIG is not empty
                    if [ -n "${BOOTFORMAT_CONFIG}" ] && echo "${type}" |grep -q spi;then
                        ${STAGING_BINDIR_NATIVE}/boot_format \
                            ${STAGING_DATADIR_NATIVE}/boot_format/${BOOTFORMAT_CONFIG} \
                            ${config}/u-boot-${type}.${UBOOT_SUFFIX} -spi ${config}/u-boot.format.bin
                        cp ${config}/u-boot.format.bin ${config}/u-boot-${type}.${UBOOT_SUFFIX}
                    fi
                fi
            done
            unset j
        done
        unset i
    fi
}

PACKAGES += "${PN}-images"
FILES_${PN}-images += "/boot"
COMPATIBLE_MACHINE = "ess-ifc1410-64b-%"