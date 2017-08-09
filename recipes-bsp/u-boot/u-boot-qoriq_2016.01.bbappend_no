
SRC_URI = "git://git.freescale.com/ppc/sdk/u-boot.git \
    	   file://fix-build-error-under-gcc6.patch \
"
#git://git.freescale.com/ppc/sdk/u-boot.git;branch=sdk-v2.0.x

SRCREV = "${AUTOREV}"


# from u-boot.inc in poky/meta/recipes-bsp/u-boot/
do_compile () {
	if [ "${@bb.utils.filter('DISTRO_FEATURES', 'ld-is-gold', d)}" ]; then
		echo "executing 'sed -i 's/$(CROSS_COMPILE)ld$/$(CROSS_COMPILE)ld.bfd/g' ${S}/config.mk'"
		sed -i 's/$(CROSS_COMPILE)ld$/$(CROSS_COMPILE)ld.bfd/g' ${S}/config.mk
	fi

	unset LDFLAGS
	unset CFLAGS
	unset CPPFLAGS

	echo "entering 'if [ ! -e ${B}/.scmversion -a ! -e ${S}/.scmversion ]'"
	if [ ! -e ${B}/.scmversion -a ! -e ${S}/.scmversion ]
	then
		echo ${UBOOT_LOCALVERSION} > ${B}/.scmversion
		echo ${UBOOT_LOCALVERSION} > ${S}/.scmversion
	fi

    if [ -n "${UBOOT_CONFIG}" ]
    then
        unset i j k
        for config in ${UBOOT_MACHINE}; do
            i=$(expr $i + 1);
            for type in ${UBOOT_CONFIG}; do
                j=$(expr $j + 1);
                if [ $j -eq $i ]
                then
                	echo "ERROR: oe_runmake"
                    oe_runmake -C ${S} O=${B}/${config} ${config}
                    oe_runmake -C ${S} O=${B}/${config} ${UBOOT_MAKE_TARGET}
                    for binary in ${UBOOT_BINARIES}; do
                        k=$(expr $k + 1);
                        if [ $k -eq $i ]; then
                        	echo "ERROR: cp ${B}/${config}/${binary} ${B}/${config}/u-boot-${type}.${UBOOT_SUFFIX}"
                            cp ${B}/${config}/${binary} ${B}/${config}/u-boot-${type}.${UBOOT_SUFFIX}
                        fi
                    done
                    unset k
                fi
            done
            unset  j
        done
        unset  i
    else
    	echo "ERROR: oe_runmake"
        oe_runmake -C ${S} O=${B} ${UBOOT_MACHINE}
        oe_runmake -C ${S} O=${B} ${UBOOT_MAKE_TARGET}
    fi

}


# from u-boot-qoriq_2016.01.bb in meta-fsl-ppc/recipes-bsp/u-boot/
do_compile_append_qoriq-ppc () {
    unset i j
    if [ -n "${UBOOT_CONFIG}" ];then
    	echo "inside 'if [ -n "${UBOOT_CONFIG}" ]'"
    	echo "uboot machines are: ${UBOOT_MACHINE}"
    	echo "uboot config are: ${UBOOT_CONFIG}"
        for config in ${UBOOT_MACHINE}; do
        	echo "we are evaluating: ${config}"
            i=`expr $i + 1`;
            for type in ${UBOOT_CONFIG}; do
            echo "we are using config: ${type}"
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
