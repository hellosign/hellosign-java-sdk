var fileCount = 1;
var signerCount = 1;
var ccCount = 1;
function removeFile(fileNumber) {
    var el = $("#fileDiv_" + fileNumber);
    if (el) {
        el.remove();
    }
    return false;
}
function removeSignerRole(signerNumber) {
    var el = $("#signerRoleDiv_" + signerNumber);
    if (el) {
        el.remove();
    }
    return false;
}
function removeCCRole(ccNumber) {
    var el = $("#ccRoleDiv_" + ccNumber);
    if (el) {
        el.remove();
    }
    return false;
}
function initTemplateDemo() {
    $('#toggleOptionalFields').click(function(e) {
        $('#optionalFields').toggle();
        return false;
    });
    $("#addFile").click(function(e) {
        fileCount++;
        $("#files").append('<div class="field-container" id="fileDiv_' + fileCount + '"><input id="file_' + fileCount + '" type="file" name="file_' + fileCount + '" />&nbsp;<button class="btn btn-xs btn-default remove" onclick="removeFile(' + fileCount + '); return false;">X</button></div>');
        return false;
    });
    $("#addSignerRole").click(function(e) {
        signerCount++;
        $("#signerRoles").append('<div class="field-container" id="signerRoleDiv_' + signerCount + '"><br /><input type="text" name="signerRole' + signerCount + '" placeholder="Signer role" />&nbsp;<button class="btn btn-xs btn-default remove" onclick="removeSignerRole(' + signerCount + '); return false;">X</button></div>');
        return false;
    });
    $("#addCCRole").click(function(e) {
        ccCount++;
        $("#ccRoles").append('<div class="field-container" id="ccRoleDiv_' + ccCount + '"><br /><input type="text" name="ccRole' + ccCount + '" placeholder="CC role" />&nbsp;<button class="btn btn-xs btn-default remove" onclick="removeCCRole(' + ccCount + '); return false;">X</button></div>');
        return false;
    });
    $("#file_1").change(function(e) {
        $("#startButton").show();
    });
    $("#startButton").hide();
}