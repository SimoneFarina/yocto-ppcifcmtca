
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
SRC_URI_append = " file://ess-ifc1410-rt.tar.gz"

do_install () {
	make install

	M=`echo ${MACHINE} | sed s/-64b//g`
	if [ "t1042d4rdb" = "${M}" ] || [ "t1040d4rdb" = "${M}" ];then
		M=t1040rdb
	fi
	echo "copy ${WORKDIR}/${M} ${S}/${M}/${M}"
	mkdir -p ${S}/${M}/${M}
	mv ${WORKDIR}/${M} ${S}/${M}/${M}

	install -d ${D}/boot/hv-cfg

	cp -r ${S}/${M}/${M}/* ${D}/boot/hv-cfg
}

