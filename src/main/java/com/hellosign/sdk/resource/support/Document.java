package com.hellosign.sdk.resource.support;

import com.hellosign.sdk.HelloSignException;
import com.hellosign.sdk.resource.AbstractResource;
import java.io.File;
import java.util.List;
import org.json.JSONObject;

/**
 * Represents a document and its associated (optional) form fields and custom fields.
 *
 * It is also populated based on the API response. This response typically contains the file name
 * and index of the document within the signature request.
 *
 * @author "Chris Paul (chris@hellosign.com)"
 */
public class Document extends AbstractResource {

    private static final String DOCUMENT_FORM_FIELDS = "form_fields";
    private static final String DOCUMENT_CUSTOM_FIELDS = "custom_fields";
    private static final String DOCUMENT_NAME = "name";
    private static final String DOCUMENT_INDEX = "index";

    private File file;

    public Document() {
        super();
    }

    public Document(JSONObject json) throws HelloSignException {
        super(json, null);
    }

    /**
     * Returns the form fields for this document.
     *
     * @return List
     */
    public List<FormField> getFormFields() {
        return getList(FormField.class, DOCUMENT_FORM_FIELDS);
    }

    /**
     * Overwrites the form fields for this document. This is useful when manually migrating form
     * fields from a template.
     *
     * @param formFields List
     * @throws HelloSignException thrown if there is a problem adding the FormFields to this
     * Document.
     */
    public void setFormFields(List<FormField> formFields) throws HelloSignException {
        clearList(DOCUMENT_FORM_FIELDS);
        for (FormField formField : formFields) {
            addFormField(formField);
        }
    }

    /**
     * Adds the form field to this document.
     *
     * @param formField FormField
     * @throws HelloSignException thrown if there is a problem adding the FormFields to this
     * Document.
     */
    public void addFormField(FormField formField) throws HelloSignException {
        if (!has(DOCUMENT_FORM_FIELDS)) {
            clearList(DOCUMENT_FORM_FIELDS);
        }
        addToList(DOCUMENT_FORM_FIELDS, formField);
    }

    /**
     * Returns the custom fields for this document.
     *
     * @return List
     */
    public List<CustomField> getCustomFields() {
        return getList(CustomField.class, DOCUMENT_CUSTOM_FIELDS);
    }

    /**
     * Returns the File associated with this document.
     *
     * @return File, or null if it hasn't been set
     */
    public File getFile() {
        return file;
    }

    /**
     * Sets the file associated with this document.
     *
     * @param file File
     */
    public void setFile(File file) {
        this.file = file;
    }

    public String getName() {
        return getString(DOCUMENT_NAME);
    }

    public void setName(String name) {
        set(DOCUMENT_NAME, name);
    }

    public boolean hasName() {
        return has(DOCUMENT_NAME);
    }

    public Integer getIndex() {
        return getInteger(DOCUMENT_INDEX);
    }

    public void setIndex(Integer index) {
        set(DOCUMENT_INDEX, index);
    }
}
