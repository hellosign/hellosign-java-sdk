package com.hellosign.sdk.resource.support;

import com.hellosign.sdk.HelloSignException;
import com.hellosign.sdk.resource.support.types.FieldType;
import com.hellosign.sdk.resource.support.types.ValidationType;
import org.json.JSONObject;

/**
 * This class represents a HelloSign Form Field object.
 */
public class FormField extends CustomField {

    private static final String FORM_FIELD_SIGNER = "signer";
    private static final String FORM_FIELD_PAGE = "page";
    private static final String FORM_FIELD_VALIDATION_TYPE = "validation_type";

    public FormField() {
        super();
    }

    public FormField(JSONObject json) throws HelloSignException {
        super(json);
    }

    /**
     * Constructor to be used when creating a new FormField object.
     *
     * @param type FormField type
     * @param name String Field Label that will be displayed to the signer
     * @param signer int index of the signer that should complete this field (0-based index)
     * @param x int x coordinate location of this field in pixels
     * @param y int y coordinate location of this field in pixels
     * @param height int height of this field in pixels
     * @param width int width of this field in pixels
     * @param page int page number of the document in which this field will be placed (1-based
     * index)
     */
    public FormField(FieldType type, String name, int signer, int x, int y, int height, int width,
        int page) {
        setType(type);
        setName(name);
        setSigner(signer);
        setX(x);
        setY(y);
        setHeight(height);
        setWidth(width);
        setPage(page);
    }

    /**
     * Returns the signer index for this form field.
     *
     * @return Integer signer index or null if not set
     */
    public Integer getSigner() {
        return getInteger(FORM_FIELD_SIGNER);
    }

    /**
     * Set the signer index that should complete this form field.
     *
     * @param signer Integer index
     */
    public void setSigner(Integer signer) {
        set(FORM_FIELD_SIGNER, signer);
    }

    /**
     * Set the page number for this component. The (x, y) coordinates will be relative to the top
     * left corner of that page.
     *
     * @param page Integer page number
     */
    public void setPage(Integer page) {
        set(FORM_FIELD_PAGE, page);
    }

    /**
     * Returns the page number this component is on.
     *
     * @return Integer page number, or null if not set
     */
    public Integer getPage() {
        return getInteger(FORM_FIELD_PAGE);
    }

    /**
     * Set the validation rule for this field. This will force the signer to enter data that
     * conforms to the validation rule.
     *
     * @param type ValidationType
     */
    public void setValidationType(ValidationType type) {
        set(FORM_FIELD_VALIDATION_TYPE, type.toString());
    }

    /**
     * Return the validation rule being used for this field
     *
     * @return ValidationType validation type, null if not set
     */
    public ValidationType getValidationType() {
        return ValidationType.valueOf(getString(FORM_FIELD_VALIDATION_TYPE));
    }

    /**
     * Return the validation rule being used for this field as a string
     *
     * @return String validation type, null if not set
     */
    public String getValidationTypeString() {
        if (has(FORM_FIELD_VALIDATION_TYPE)) {
            return getValidationType().toString();
        }
        return null;
    }

    /**
     * Set this field as required.
     *
     * @param isRequired boolean true if field is required by signer
     * @deprecated Use {@link #setIsRequired(Boolean)}
     */
    @Deprecated
    public void setRequired(boolean isRequired) {
        setIsRequired(isRequired);
    }

    @Override
    public void setValue(String value) {
        throw new UnsupportedOperationException("Setting value is not allowed for form fields, see custom fields.");
    }
}
