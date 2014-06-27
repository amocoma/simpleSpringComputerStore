// Copyright (c) 2013, salesforce.com, inc.
// All rights reserved.
//
// Redistribution and use in source and binary forms, with or without modification, are permitted provided
// that the following conditions are met:
//
//     Redistributions of source code must retain the above copyright notice, this list of conditions and the
//     following disclaimer.
//
//     Redistributions in binary form must reproduce the above copyright notice, this list of conditions and
//     the following disclaimer in the documentation and/or other materials provided with the distribution.
//
//     Neither the name of salesforce.com, inc. nor the names of its contributors may be used to endorse or
//     promote products derived from this software without specific prior written permission.
//
// THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED
// WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A
// PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
// ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED
// TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
// HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
// NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
// POSSIBILITY OF SUCH DAMAGE.
(function ($$){

    var sr, mobile, postType, thumbnailUrl, parameter, title;

    myPublisher = {

        init : function(signedRequest, isMobile) {
            sr = signedRequest;
            mobile = isMobile;
            postType= "Canvas";
        },

        // Auto resize the iframe to fit the current content.
        resize : function() {
            $$.client.resize(sr.client);
        },


        selectPostType : function(e) {
            console.log("got click", e);
            postType = e;
            // Enable the share button
            $$.client.publish(sr.client, {name : "publisher.setValidForSubmit", payload : true});
        },
        
        contentSet : function(value) {
            $$.client.publish(sr.client, {name : "publisher.setValidForSubmit", payload : true});
            $$.byId('parameters').value = JSON.stringify(value);
            title= "Show " + value.title;
            parameter = JSON.stringify(value);
            
        },
        contentDeleted : function() {
            $$.client.publish(sr.client, {name : "publisher.setValidForSubmit", payload : false});
            $$.byId('parameters').value = JSON.stringify({});
            title="";
            parameter = JSON.stringify({});
        },

        canvasOptions : function(elem, option) {
            var bool = Sfdc.canvas.indexOf(sr.context.application.options, option) == -1;
            elem.innerHTML = (bool) ? "&#x2713;" : "&#x2717;";
            elem.style.color = (bool) ? "green" : "red";
        },

        updateContent : function() {
            if (!mobile) {
                $$.byId('name').innerHTML = sr.context.user.firstName + " " + sr.context.user.lastName;
                $$.byId('location').innerHTML = sr.context.environment.displayLocation;
                myPublisher.canvasOptions($$.byId('header-enabled'), "HideHeader");
                myPublisher.canvasOptions($$.byId('share-enabled'), "HideShare");
            }
        },

        handlers : function() {

            var handlers = {
                onSetupPanel : function (payload) {
                    myPublisher.resize();   // Do I want to do this on iphone?
                },
                onShowPanel : function(payload) {
                },
                onClearPanelState : function(payload) {
                    //myPublisher.clearPostTypes();
                },
                onSuccess : function() {
                    //myPublisher.logEvent("success");
                },
                onFailure : function (payload) {
                    //myPublisher.clearPostTypes();
                    if (payload && payload.errors && payload.errors.message) {
                        alert("Error: " + payload.errors.message);
                    }
                },
                onGetPayload : function() {
                    var p = {};
                    if (postType === 'Canvas') {
                        // Example of a Canvas Post
                        p.feedItemType = "CanvasPost";
                        p.auxText = $$.byId('auxText').value;
                        p.namespace =  sr.context.application.namespace;
                        p.developerName =  sr.context.application.developerName;
                        p.title = title;
                        p.description = $$.byId('description').value;
                        p.parameters = parameter;
                        p.thumbnailUrl = window.location.origin + '/img/canvaslogo.png';
                    }
                    $$.client.publish(sr.client, {name : 'publisher.setPayload', payload : p});
                }
            };

            return {
                subscriptions : [
                    {name : 'publisher.setupPanel', onData : handlers.onSetupPanel},
                    {name : 'publisher.showPanel', onData : handlers.onShowPanel},
                    {name : 'publisher.clearPanelState',  onData : handlers.onClearPanelState},
                    {name : 'publisher.failure', onData : handlers.onFailure},
                    {name : 'publisher.success', onData : handlers.onSuccess},
                    {name : 'publisher.getPayload', onData : handlers.onGetPayload}
                ]
            };
        }
    };
}(Sfdc.canvas));
