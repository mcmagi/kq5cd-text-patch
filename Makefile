INSTALL_DIR=D:/games/Sierra/KQ5CD
PY=python
VERSION=0.0.1

GAME_DIR=kq5cd
BUILD_DIR=build
PATCH_DIR=${BUILD_DIR}/patch
TEXT_DIR=text
SRC_DIR=src
TSV_2_TEX=${PY} tsv2tex.py

ZIP_FILE=${BUILD_DIR}/kq5cd-text-patch-${VERSION}.zip

TEX_FILES=${PATCH_DIR}/300.tex \
		  ${PATCH_DIR}/301.tex \
		  ${PATCH_DIR}/330.tex \
		  ${PATCH_DIR}/331.tex \
		  ${PATCH_DIR}/340.tex \
		  ${PATCH_DIR}/341.tex \
		  ${PATCH_DIR}/350.tex \
		  ${PATCH_DIR}/351.tex \
		  ${PATCH_DIR}/370.tex \
		  ${PATCH_DIR}/371.tex \
		  ${PATCH_DIR}/390.tex \
		  ${PATCH_DIR}/391.tex

SRC_FILES=${SRC_DIR}/sci.kq5.sh \
		  ${SRC_DIR}/Main.sc \
		  ${SRC_DIR}/Interface.sc \
		  ${SRC_DIR}/Talker.sc

SCR_FILES=${PATCH_DIR}/0.SCR \
		  ${PATCH_DIR}/255.SCR \
		  ${PATCH_DIR}/928.SCR

all: text script ${ZIP_FILE}

install:
	cp ${TEX_FILES} ${GAME_DIR}

import:
	cp ${INSTALL_DIR}/*.DRV ${INSTALL_DIR}/*.TEX ${INSTALL_DIR}/*.SCR ${INSTALL_DIR}/*.SYN ${INSTALL_DIR}/*.SND \
		${INSTALL_DIR}/RESOURCE.* ${INSTALL_DIR}/AUDIO001.* ${INSTALL_DIR}/SCI*.* ${INSTALL_DIR}/SIERRA.EXE \
		${INSTALL_DIR}/INTERP.TXT ${INSTALL_DIR}/VERSION ${GAME_DIR}

src: ${SRC_FILES}

${SRC_DIR}/%.sh: ${GAME_DIR}/src/%.sh
	cp $< $@

${SRC_DIR}/%.sc: ${GAME_DIR}/src/%.sc
	cp $< $@

text: ${TEX_FILES}

${PATCH_DIR}/%0.tex ${PATCH_DIR}/%1.tex: ${TEXT_DIR}/%0.tsv
	${TSV_2_TEX} $< ${PATCH_DIR}

script: ${SCR_FILES}

${PATCH_DIR}/%.scr: ${GAME_DIR}/%.scr
	cp $< $@

${ZIP_FILE}: ${TEX_FILES} ${SCR_FILES}
	cd ${PATCH_DIR} && zip ../../${ZIP_FILE} *.scr *.tex

