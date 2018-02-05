(function() {
    'use strict';

    angular
        .module('jhipstergatwayApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('telephone', {
            parent: 'entity',
            url: '/telephone',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'jhipstergatwayApp.telephone.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/telephone/telephones.html',
                    controller: 'TelephoneController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('telephone');
                    $translatePartialLoader.addPart('');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('telephone-detail', {
            parent: 'telephone',
            url: '/telephone/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'jhipstergatwayApp.telephone.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/telephone/telephone-detail.html',
                    controller: 'TelephoneDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('telephone');
                    $translatePartialLoader.addPart('');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Telephone', function($stateParams, Telephone) {
                    return Telephone.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'telephone',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('telephone-detail.edit', {
            parent: 'telephone-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/telephone/telephone-dialog.html',
                    controller: 'TelephoneDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Telephone', function(Telephone) {
                            return Telephone.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('telephone.new', {
            parent: 'telephone',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/telephone/telephone-dialog.html',
                    controller: 'TelephoneDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                countryCode: null,
                                areaCode: null,
                                number: null,
                                type: null,
                                mobileOTP: null,
                                mobileOTPDate: null,
                                mobileOTPExpired: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('telephone', null, { reload: 'telephone' });
                }, function() {
                    $state.go('telephone');
                });
            }]
        })
        .state('telephone.edit', {
            parent: 'telephone',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/telephone/telephone-dialog.html',
                    controller: 'TelephoneDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Telephone', function(Telephone) {
                            return Telephone.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('telephone', null, { reload: 'telephone' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('telephone.delete', {
            parent: 'telephone',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/telephone/telephone-delete-dialog.html',
                    controller: 'TelephoneDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Telephone', function(Telephone) {
                            return Telephone.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('telephone', null, { reload: 'telephone' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
