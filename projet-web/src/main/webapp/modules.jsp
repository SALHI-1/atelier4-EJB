<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestion des Modules</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-50 min-h-screen">

<div class="container mx-auto px-4 py-8 max-w-7xl">

    <!-- Header -->
    <div class="mb-8">
        <h1 class="text-3xl font-bold text-gray-800 mb-2">Gestion des Modules</h1>
        <p class="text-gray-600">Liste des modules disponibles et ajout de nouveaux modules</p>
    </div>

    <!-- Table Section -->
    <div class="bg-white rounded-lg shadow-md overflow-hidden mb-8">
        <div class="px-6 py-4 bg-gradient-to-r from-teal-500 to-teal-600">
            <h2 class="text-xl font-semibold text-white">Liste des Modules</h2>
        </div>

        <div class="overflow-x-auto">
            <table class="w-full">
                <thead class="bg-gray-50">
                <tr>
                    <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">ID</th>
                    <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Nom du Module</th>
                    <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Semestre</th>
                </tr>
                </thead>
                <tbody class="bg-white divide-y divide-gray-200">
                <c:forEach var="i" begin="0" end="${listeModules.size() - 1}">
                    <tr class="hover:bg-gray-50 transition-colors">
                        <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-500">
                                ${listeModules[i].id_module}
                        </td>
                        <td class="px-6 py-4 text-sm font-medium text-gray-900">
                                ${listeModules[i].nom_module}
                        </td>
                        <td class="px-6 py-4 whitespace-nowrap text-sm">
                                <span class="inline-flex items-center px-3 py-1 rounded-full text-xs font-medium bg-teal-100 text-teal-800">
                                        ${listeModules[i].semestre}
                                </span>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

    <!-- Form Section -->
    <div class="bg-white rounded-lg shadow-md overflow-hidden">
        <div class="px-6 py-4 bg-gradient-to-r from-cyan-500 to-cyan-600">
            <h2 class="text-xl font-semibold text-white">Ajouter un Nouveau Module</h2>
        </div>

        <form action="modules" method="post" class="p-6">
            <div class="grid grid-cols-1 md:grid-cols-2 gap-6">

                <!-- Nom du Module -->
                <div>
                    <label class="block text-sm font-medium text-gray-700 mb-2">
                        Nom du Module <span class="text-red-500">*</span>
                    </label>
                    <input type="text" name="nom_module" required
                           class="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-cyan-500 focus:border-transparent transition-all"
                           placeholder="Ex: Programmation Java">
                </div>

                <!-- Semestre -->
                <div>
                    <label class="block text-sm font-medium text-gray-700 mb-2">
                        Semestre <span class="text-red-500">*</span>
                    </label>
                    <input type="text" name="semestre" required
                           class="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-cyan-500 focus:border-transparent transition-all"
                           placeholder="Ex: S5">
                </div>
            </div>

            <!-- Submit Button -->
            <div class="mt-6">
                <button type="submit"
                        class="w-full md:w-auto px-6 py-3 bg-cyan-600 hover:bg-cyan-700 text-white font-medium rounded-lg shadow-sm transition-colors focus:outline-none focus:ring-2 focus:ring-cyan-500 focus:ring-offset-2">
                    <span class="flex items-center justify-center">
                        <svg class="w-5 h-5 mr-2" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 6.253v13m0-13C10.832 5.477 9.246 5 7.5 5S4.168 5.477 3 6.253v13C4.168 18.477 5.754 18 7.5 18s3.332.477 4.5 1.253m0-13C13.168 5.477 14.754 5 16.5 5c1.747 0 3.332.477 4.5 1.253v13C19.832 18.477 18.247 18 16.5 18c-1.746 0-3.332.477-4.5 1.253"/>
                        </svg>
                        Ajouter le module
                    </span>
                </button>
            </div>
        </form>
    </div>

</div>

</body>
</html>