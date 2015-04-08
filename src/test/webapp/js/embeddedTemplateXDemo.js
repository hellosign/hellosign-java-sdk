function initTemplates() {
    var templateFields = $("#templateFields");
    templateFields.hide();
    $("#startButton").hide();
    var selectList = $("#templates");
    for (var i = 0; i < templates.length; i++) {
        selectList.append('<option value="' + i + '">' + templates[i].title
                + '</option>');
    }
    selectList
            .change(function(e) {
                templateFields.show();
                $('#signersContainer').text('(None)');
                $('#ccsContainer').text('(None)');
                $('#customFieldsContainer').text('(None)');
                var template = templates[$("option:selected", this).attr(
                        "value")];
                if (template) {
                    if (template.signer_roles.length > 0) {
                        $('#signersContainer').empty();
                    }
                    for (var i = 0; i < template.signer_roles.length; i++) {
                        var signerRole = template.signer_roles[i];
                        var newOptionStr = '<label for="signerRole_'
                                + signerRole.name
                                + '">'
                                + (signerRole.order != null ? signerRole.order
                                        : '')
                                + signerRole.name
                                + ':</label>&nbsp;<input type="text" name="signerRole_email_'
                                + signerRole.name
                                + '" placeholder="Email address"/> '
                                + '<input type="text" name="signerRole_name_'
                                + signerRole.name
                                + '" placeholder="Name"/><br />';
                        var newSignerFields = $(newOptionStr);
                        $('#signersContainer').append(newSignerFields);
                    }
                    if (template.cc_roles.length > 0) {
                        $('#ccsContainer').text('(None)');
                    }
                    for (var i = 0; i < template.cc_roles.length; i++) {
                        var ccRole = template.cc_roles[i].name;
                        var newCCFieldStr = '<label for="ccRole_'
                                + ccRole
                                + '">'
                                + ccRole
                                + ':</label>&nbsp;<input type="text" name="ccRole_'
                                + ccRole
                                + '" placeholder="Email address"/><br />'
                        var newCCFields = $(newCCFieldStr);
                        $('#ccsContainer').append(newCCFields);
                    }
                    if (template.custom_fields.length > 0) {
                        $('#customFieldsContainer').text('(None)');
                    }
                    for (var i = 0; i < template.custom_fields.length; i++) {
                        var cf = template.custom_fields[i];
                        var newCFFieldStr = '<label for="cf_' + cf.name + '">'
                                + cf.name
                                + ':</label>&nbsp;<input type="text" name="cf_'
                                + cf.name + '" placeholder="' + cf.type
                                + '"/><br />';
                        var newCFField = $(newCFFieldStr);
                        $('#customFieldsContainer').append(newCFField);
                    }
                    $('#templateId').val(template.template_id);
                    $('#startButton').show();
                } else {
                    $('#templateFields').hide();
                    $('#startButton').hide();
                }
            });
};