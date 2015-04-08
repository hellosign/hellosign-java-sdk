function removeFile(fileNumber) {
    var el = $("#fileDiv_" + fileNumber);
    if (el) {
        el.remove();
    }
    return false;
}
function removeSigner(signerNumber) {
    var el = $("#signerDiv_" + signerNumber);
    if (el) {
        el.remove();
    }
    return false;
}
function initEmbeddedRequesting() {
    $('#toggleOptionalFields').click(function(e) {
        $('#optionalFields').toggle();
        return false;
    });
    var fileCount = 1;
    var signerCount = 1;
    $("#startButton").hide();
    $("#addFile").click(function(e) {
        fileCount++;
        $("#files").append('<div class="field-container" id="fileDiv_' + fileCount + '"><input id="file_' + fileCount + '" type="file" name="file_' + fileCount + '" />&nbsp;<button class="btn btn-xs btn-default remove" onclick="removeFile(' + fileCount + '); return false;">X</button></div>');
        return false;
    });
    $("#addSigner").click(function(e) {
        signerCount++;
        $("#signers").append('<div class="field-container" id="signerDiv_' + signerCount + '"><br /><input type="text" name="signer' + signerCount + '_name" placeholder="Signer name" /> <input type="text" name="signer' + signerCount + '_email" placeholder="Signer email" />&nbsp;<button class="btn btn-xs btn-default remove" onclick="removeSigner(' + signerCount + '); return false;">X</button></div>');
        return false;
    });
    $("#file_1").change(function(e) {
        $("#startButton").show();
    });
}