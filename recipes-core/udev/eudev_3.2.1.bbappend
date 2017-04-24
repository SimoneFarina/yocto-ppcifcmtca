FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI_append_qoriq-ppc = " \
    file://0001-skip-rules-for-mmc-rpmb-partition.patch \
"
