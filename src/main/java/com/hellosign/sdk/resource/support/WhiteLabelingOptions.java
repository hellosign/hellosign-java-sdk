package com.hellosign.sdk.resource.support;

/**
 * The MIT License (MIT)
 * 
 * Copyright (C) 2017 hellosign.com
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONObject;

import com.hellosign.sdk.HelloSignException;
import com.hellosign.sdk.resource.AbstractResource;

public class WhiteLabelingOptions extends AbstractResource {

    public static final String WHITE_LABELING_OPTIONS_KEY = "white_labeling_options";
    public static final String WHITE_LABELING_OPTIONS_PAGE_BACKGROUND_COLOR = "page_background_color";
    public static final String WHITE_LABELING_OPTIONS_HEADER_BACKGROUND_COLOR = "header_background_color";
    public static final String WHITE_LABELING_OPTIONS_TEXT_COLOR_1 = "text_color1";
    public static final String WHITE_LABELING_OPTIONS_TEXT_COLOR_2 = "text_color2";
    public static final String WHITE_LABELING_OPTIONS_LINK_COLOR = "link_color";
    public static final String WHITE_LABELING_OPTIONS_PRIMARY_BUTTON_COLOR = "primary_button_color";
    public static final String WHITE_LABELING_OPTIONS_PRIMARY_BUTTON_TEXT_COLOR = "primary_button_text_color";
    public static final String WHITE_LABELING_OPTIONS_PRIMARY_BUTTON_COLOR_HOVER = "primary_button_color_hover";
    public static final String WHITE_LABELING_OPTIONS_PRIMARY_BUTTON_TEXT_COLOR_HOVER = "primary_button_text_color_hover";
    public static final String WHITE_LABELING_OPTIONS_SECONDARY_BUTTON_COLOR = "secondary_button_color";
    public static final String WHITE_LABELING_OPTIONS_SECONDARY_BUTTON_TEXT_COLOR = "secondary_button_text_color";
    public static final String WHITE_LABELING_OPTIONS_SECONDARY_BUTTON_COLOR_HOVER = "secondary_button_color_hover";
    public static final String WHITE_LABELING_OPTIONS_SECONDARY_BUTTON_TEXT_COLOR_HOVER = "secondary_button_text_color_hover";
    public static final String WHITE_LABELING_OPTIONS_LEGAL_VERSION = "legal_version";

    public static final String LEGAL_VERSION_1 = "terms1"; // signer agrees "to be legally bound to the HelloSign Terms of Service."
    public static final String LEGAL_VERSION_2 = "terms2"; // signer agrees "to the eSignature Terms of Service."

    private static Pattern pattern;
    private Matcher matcher;
    private static final String HEX_PATTERN = "^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$";

    static {
        pattern = Pattern.compile(HEX_PATTERN);
    }

    public WhiteLabelingOptions() {
    }

    public WhiteLabelingOptions(JSONObject json) throws HelloSignException {
        super(json, WHITE_LABELING_OPTIONS_KEY);
    }

    /**
     * Validate hex with regular expression
     * 
     * @param hex string for validation
     * @return true valid hex, false invalid hex
     */
    public boolean validateColor(final String hex) {
        matcher = pattern.matcher(hex);
        return matcher.matches();
    }

    /**
     * Helper method to validate and set a color.
     * 
     * @param key String key
     * @param color String color hex code
     * @throws HelloSignException thrown if the color string is an invalid hex
     *         string
     */
    private void setColor(String key, String color) throws HelloSignException {
        if (!validateColor(color)) {
            throw new HelloSignException("Invalid color: " + color);
        }
        set(key, color);
    }

    /**
     * Get the signer page background color.
     * 
     * @return String hex color code
     */
    public String getPageBackgroundColor() {
        return getString(WHITE_LABELING_OPTIONS_PAGE_BACKGROUND_COLOR);
    }

    /**
     * Set the signer page background color.
     * 
     * @param color String hex color code
     * @throws HelloSignException thrown if the color string is an invalid hex
     *         string
     */
    public void setPageBackgroundColor(String color) throws HelloSignException {
        setColor(WHITE_LABELING_OPTIONS_PAGE_BACKGROUND_COLOR, color);
    }

    /**
     * Get the signer page header background color.
     * 
     * @return String hex color code
     */
    public String getHeaderBackgroundColor() {
        return getString(WHITE_LABELING_OPTIONS_HEADER_BACKGROUND_COLOR);
    }

    /**
     * Set the signer page header background color.
     * 
     * @param color String hex color code
     * @throws HelloSignException thrown if the color string is an invalid hex
     *         string
     */
    public void setHeaderBackgroundColor(String color) throws HelloSignException {
        setColor(WHITE_LABELING_OPTIONS_HEADER_BACKGROUND_COLOR, color);
    }

    /**
     * Get the signer page text 1 color.
     * 
     * @return String hex color code
     */
    public String getTextColor1() {
        return getString(WHITE_LABELING_OPTIONS_TEXT_COLOR_1);
    }

    /**
     * Set the signer page text 1 color.
     * 
     * @param color String hex color code
     * @throws HelloSignException thrown if the color string is an invalid hex
     *         string
     */
    public void setTextColor1(String color) throws HelloSignException {
        setColor(WHITE_LABELING_OPTIONS_TEXT_COLOR_1, color);
    }

    /**
     * Get the signer page text 2 color.
     * 
     * @return String hex color code
     */
    public String getTextColor2() {
        return getString(WHITE_LABELING_OPTIONS_TEXT_COLOR_2);
    }

    /**
     * Set the signer page text 2 color.
     * 
     * @param color String hex color code
     * @throws HelloSignException thrown if the color string is an invalid hex
     *         string
     */
    public void setTextColor2(String color) throws HelloSignException {
        setColor(WHITE_LABELING_OPTIONS_TEXT_COLOR_2, color);
    }

    /**
     * Get the signer page link color.
     * 
     * @return String hex color code
     */
    public String getLinkColor() {
        return getString(WHITE_LABELING_OPTIONS_LINK_COLOR);
    }

    /**
     * Set the signer page link color.
     * 
     * @param color String hex color code
     * @throws HelloSignException thrown if the color string is an invalid hex
     *         string
     */
    public void setLinkColor(String color) throws HelloSignException {
        setColor(WHITE_LABELING_OPTIONS_LINK_COLOR, color);
    }

    /**
     * Get the signer page primary button color.
     * 
     * @return String hex color code
     */
    public String getPrimaryButtonColor() {
        return getString(WHITE_LABELING_OPTIONS_PRIMARY_BUTTON_COLOR);
    }

    /**
     * Set the signer page primary button color.
     * 
     * @param color String hex color code
     * @throws HelloSignException thrown if the color string is an invalid hex
     *         string
     */
    public void setPrimaryButtonColor(String color) throws HelloSignException {
        setColor(WHITE_LABELING_OPTIONS_PRIMARY_BUTTON_COLOR, color);
    }

    /**
     * Get the signer page primary button text color.
     * 
     * @return String hex color code
     */
    public String getPrimaryButtonTextColor() {
        return getString(WHITE_LABELING_OPTIONS_PRIMARY_BUTTON_TEXT_COLOR);
    }

    /**
     * Set the signer page primary button text color.
     * 
     * @param color String hex color code
     * @throws HelloSignException thrown if the color string is an invalid hex
     *         string
     */
    public void setPrimaryButtonTextColor(String color) throws HelloSignException {
        setColor(WHITE_LABELING_OPTIONS_PRIMARY_BUTTON_TEXT_COLOR, color);
    }

    /**
     * Get the signer page primary button hover color.
     * 
     * @return String hex color code
     */
    public String getPrimaryButtonHoverColor() {
        return getString(WHITE_LABELING_OPTIONS_PRIMARY_BUTTON_COLOR_HOVER);
    }

    /**
     * Set the signer page primary button hover color.
     * 
     * @param color String hex color code
     * @throws HelloSignException thrown if the color string is an invalid hex
     *         string
     */
    public void setPrimaryButtonHoverColor(String color) throws HelloSignException {
        setColor(WHITE_LABELING_OPTIONS_PRIMARY_BUTTON_COLOR_HOVER, color);
    }

    /**
     * Get the signer page primary button text hover color.
     * 
     * @return String hex color code
     */
    public String getPrimaryButtonTextHoverColor() {
        return getString(WHITE_LABELING_OPTIONS_PRIMARY_BUTTON_TEXT_COLOR_HOVER);
    }

    /**
     * Set the signer page primary button text hover color.
     * 
     * @param color String hex color code
     * @throws HelloSignException thrown if the color string is an invalid hex
     *         string
     */
    public void setPrimaryButtonTextHoverColor(String color) throws HelloSignException {
        setColor(WHITE_LABELING_OPTIONS_PRIMARY_BUTTON_TEXT_COLOR_HOVER, color);
    }

    /**
     * Get the signer page secondary button color.
     * 
     * @return String hex color code
     */
    public String getSecondaryButtonColor() {
        return getString(WHITE_LABELING_OPTIONS_SECONDARY_BUTTON_COLOR);
    }

    /**
     * Set the signer page secondary button color.
     * 
     * @param color String hex color code
     * @throws HelloSignException thrown if the color string is an invalid hex
     *         string
     */
    public void setSecondaryButtonColor(String color) throws HelloSignException {
        setColor(WHITE_LABELING_OPTIONS_SECONDARY_BUTTON_COLOR, color);
    }

    /**
     * Get the signer page secondary button text color.
     * 
     * @return String hex color code
     */
    public String getSecondaryButtonTextColor() {
        return getString(WHITE_LABELING_OPTIONS_SECONDARY_BUTTON_TEXT_COLOR);
    }

    /**
     * Set the signer page secondary button text color.
     * 
     * @param color String hex color code
     * @throws HelloSignException thrown if the color string is an invalid hex
     *         string
     */
    public void setSecondaryButtonTextColor(String color) throws HelloSignException {
        setColor(WHITE_LABELING_OPTIONS_SECONDARY_BUTTON_TEXT_COLOR, color);
    }

    /**
     * Get the signer page secondary button hover color.
     * 
     * @return String hex color code
     */
    public String getSecondaryButtonHoverColor() {
        return getString(WHITE_LABELING_OPTIONS_SECONDARY_BUTTON_COLOR_HOVER);
    }

    /**
     * Set the signer page secondary button hover color.
     * 
     * @param color String hex color code
     * @throws HelloSignException thrown if the color string is an invalid hex
     *         string
     */
    public void setSecondaryButtonHoverColor(String color) throws HelloSignException {
        setColor(WHITE_LABELING_OPTIONS_SECONDARY_BUTTON_COLOR_HOVER, color);
    }

    /**
     * Get the signer page secondary button text hover color.
     * 
     * @return String hex color code
     */
    public String getSecondaryButtonTextHoverColor() {
        return getString(WHITE_LABELING_OPTIONS_SECONDARY_BUTTON_TEXT_COLOR_HOVER);
    }

    /**
     * Set the signer page secondary button text hover color.
     * 
     * @param color String hex color code
     * @throws HelloSignException thrown if the color string is an invalid hex
     *         string
     */
    public void setSecondaryButtonTextHoverColor(String color) throws HelloSignException {
        setColor(WHITE_LABELING_OPTIONS_SECONDARY_BUTTON_TEXT_COLOR_HOVER, color);
    }

    /**
     * Set the legal terms version.
     * @param version String use constants LEGAL_VERSION_1 or LEGAL_VERSION_2
     */
    public void setLegalVersion(String version) {
        set(WHITE_LABELING_OPTIONS_LEGAL_VERSION, version);
    }
}
