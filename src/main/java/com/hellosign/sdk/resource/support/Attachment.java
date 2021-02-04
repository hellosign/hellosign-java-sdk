package com.hellosign.sdk.resource.support;

import org.apache.commons.lang3.StringUtils;

public class Attachment {
    String name;
    String instructions;
    int signer_index;
    boolean isRequired;
    int signer;

    public Attachment(){
    }

    /***
     * Method to add/request attachments with Signature Request.
     * @param name : Signer name
     * @param instructions : Instruction for Signer
     * @param signer_index : Signer Index (It should be in accordance with signer's order: first signer is 0, second is
     *                    1 and so on.. )
     * @param isRequired : Whether it is mandatory or not.
     */
    public Attachment(String name, String instructions, int signer_index, boolean isRequired){
      setName(name);
      setInstructions(instructions);
      setSigner_index(signer_index);
      setRequired(isRequired);
    }

    /**
     * Get Instruction for signer.
     */
    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        if(!StringUtils.isBlank(instructions)){
            this.instructions = instructions;
        }
    }

    public int getSigner() {
        return signer;
    }

    /**
     * Provide signer_index, in accordance to the order, signer is added.
     * Example : first signer : signer_index = 0
     *           second signer : signer_index = 1
     */
    public void setSigner_index(int signer_index) {
            this.signer_index= signer_index;
    }

    public int getSigner_index() {
        return signer_index;
    }


    public boolean isRequired() {
        return isRequired;
    }

    public void setRequired(boolean required) {
        if(required){
            isRequired = required;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(!StringUtils.isBlank(name)){
            this.name = name;
        }
    }

}

