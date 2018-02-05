(function() {
    'use strict';

    angular
        .module('jhipstergatwayApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('taluq', {
            parent: 'entity',
            url: '/taluq',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'jhipstergatwayApp.taluq.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/taluq/taluqs.html',
                    controller: 'TaluqController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('taluq');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('taluq-detail', {
            parent: 'taluq',
            url: '/taluq/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'jhipstergatwayApp.taluq.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/taluq/taluq-detail.html',
                    controller: 'TaluqDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('taluq');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Taluq', function($stateParams, Taluq) {
                    return Taluq.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'taluq',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('taluq-detail.edit', {
            parent: 'taluq-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/taluq/taluq-dialog.html',
                    controller: 'TaluqDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Taluq', function(Taluq) {
                            return Taluq.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('taluq.new', {
            parent: 'taluq',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/taluq/taluq-dialog.html',
                    controller: 'TaluqDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                name: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('taluq', null, { reload: 'taluq' });
                }, function() {
                    $state.go('taluq');
                });
            }]
        })
        .state('taluq.edit', {
            parent: 'taluq',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/taluq/taluq-dialog.html',
                    controller: 'TaluqDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Taluq', function(Taluq) {
                            return Taluq.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('taluq', null, { reload: 'taluq' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('taluq.delete', {
            parent: 'taluq',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/taluq/taluq-delete-dialog.html',
                    controller: 'TaluqDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Taluq', function(Taluq) {
                            return Taluq.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('taluq', null, { reload: 'taluq' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
