package com.particle.global.tool.pinyin;

import cn.hutool.core.util.StrUtil;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import java.util.ArrayList;

/**
 * <p>
 *
 * </p>
 *
 * @author yangwei
 * @since 2022-04-13 19:01
 */
public class PinyinTool {

	/**
	 * 汉字中文转拼音
	 * @param hanzi
	 * @return
	 * @throws BadHanyuPinyinOutputFormatCombination
	 */
	public static Pinyin getPinyin(String hanzi) throws BadHanyuPinyinOutputFormatCombination {
		return getPinyin(hanzi,false);
	}
	/**
	 * 汉字中文转拼音
	 * @param hanzi
	 * @param caps 返回大小写 true=返回大写，false=返回小写
	 * @return
	 * @throws BadHanyuPinyinOutputFormatCombination
	 */
	public static Pinyin getPinyin(String hanzi, boolean caps) throws BadHanyuPinyinOutputFormatCombination {
		Pinyin pinyinDto = new Pinyin();
		pinyinDto.setPinyins(new ArrayList<String[]>());
		pinyinDto.setOriginHanzi(hanzi);
		HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
		format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		format.setVCharType(HanyuPinyinVCharType.WITH_V);
		char[] chars = hanzi.toCharArray();

		for (char aChar : chars) {

			String[] strings = PinyinHelper.toHanyuPinyinStringArray(aChar, format);
			if (strings == null || strings.length == 0) {
				continue;
			}
			String defaultPinyin = strings[0];
			String defaultPinyinFirst = defaultPinyin.substring(0,1);
			pinyinDto.getPinyins().add(strings);
			if (StrUtil.isEmpty(pinyinDto.getFirst())) {
				pinyinDto.setFirst(defaultPinyinFirst);
			}
			if (StrUtil.isEmpty(pinyinDto.getSimple())) {
				pinyinDto.setSimple(defaultPinyinFirst);
			}else {
				pinyinDto.setSimple(pinyinDto.getSimple() + defaultPinyinFirst);

			}
			if (StrUtil.isEmpty(pinyinDto.getFull())) {
				pinyinDto.setFull(defaultPinyin);
			}else {

				pinyinDto.setFull(pinyinDto.getFull() + defaultPinyin);
			}
		}

		if(caps){
			pinyinDto.setFull(pinyinDto.getFull().toUpperCase());
			pinyinDto.setFirst(pinyinDto.getFirst().toUpperCase());
			pinyinDto.setSimple(pinyinDto.getSimple().toUpperCase());
			for (String[] pinyin : pinyinDto.getPinyins()) {
				for (int i = 0; i < pinyin.length; i++) {
					pinyin[i] = pinyin[i].toUpperCase();
				}
			}
		}

		return pinyinDto;
	}
}
